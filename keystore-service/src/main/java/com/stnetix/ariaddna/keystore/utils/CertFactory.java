package com.stnetix.ariaddna.keystore.utils;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.x509.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.Date;

/**
 * Created by alexkotov on 20.04.17.
 */
public class CertFactory {
    private static final CertFactory CERT_FACTORY = new CertFactory();

    private CertFactory() {
    }

    public static CertFactory getCertFactory() {
        return CERT_FACTORY;
    }

    private static final AriaddnaLogger LOGGER;
    private static final Date FROM;
    private static final Date TO;
    private static final int DAYS;
    private static final String CRYPTO_ALGORITHM_RSA;
    private static final String CRYPTO_ALGORITHM_SHA1RSA;
    private static final int CERTIFICATE_SIZE;
    private static final String SUBJECT_CN;
    private static final String SUBJECT_L_C;

    static {
        LOGGER = AriaddnaLogger.getLogger(CertFactory.class);
        FROM = new Date(System.currentTimeMillis());
        DAYS = 365 * 10;
        TO = new Date(System.currentTimeMillis() + 86400000L * DAYS);
        CRYPTO_ALGORITHM_RSA = "RSA";
        CRYPTO_ALGORITHM_SHA1RSA = "SHA1withRSA";
        CERTIFICATE_SIZE = 1024;
        SUBJECT_CN = "CN=";
        SUBJECT_L_C = "L=Russia, C=RU";

    }

    public File getNewCertificate(String alias) throws KeyStoreException {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(CRYPTO_ALGORITHM_RSA);
            keyPairGenerator.initialize(CERTIFICATE_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            X509CertInfo certInfo = new X509CertInfo();
            CertificateValidity interval = new CertificateValidity(FROM, TO);
            BigInteger sn = new BigInteger(64, new SecureRandom());
            X500Name owner = new X500Name(SUBJECT_CN + alias + ", "+SUBJECT_L_C);

            certInfo.set(X509CertInfo.VALIDITY, interval);
            certInfo.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(sn));
            certInfo.set(X509CertInfo.SUBJECT, owner);
            certInfo.set(X509CertInfo.ISSUER, owner);
            certInfo.set(X509CertInfo.KEY, new CertificateX509Key(keyPair.getPublic()));
            certInfo.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
            AlgorithmId algorithm = new AlgorithmId(AlgorithmId.md2WithRSAEncryption_oid);
            certInfo.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algorithm));

            X509CertImpl cert = new X509CertImpl(certInfo);
            cert.sign(privateKey, CRYPTO_ALGORITHM_SHA1RSA);

            algorithm = (AlgorithmId) cert.get(X509CertImpl.SIG_ALG);
            certInfo.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, algorithm);
            cert = new X509CertImpl(certInfo);
            cert.sign(privateKey, CRYPTO_ALGORITHM_SHA1RSA);

            File certFile = new File(alias + ".cer");
            if (certFile.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(certFile);
                fos.write(cert.getEncoded());
                fos.close();
            }
            LOGGER.info("Certificate generated with filename {}", certFile.getAbsolutePath());
            CertificateDTO storedCert = PersistHelper.getInstance().storeCertificete(new CertificateDTO(alias,true));
            LOGGER.info("Certificate stored id DB with id {}", storedCert.getId());
            return certFile;

        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    public boolean isValid(File certFile) throws KeyStoreException {
        try {
            X509CertImpl cert = (X509CertImpl) getCertByFile(certFile);
            long notBefore = cert.getNotBefore().getTime();
            long notAfter = cert.getNotAfter().getTime();
            long now = System.currentTimeMillis();
            LOGGER.info("Certificate {} is " + (now >= notBefore && now <= notAfter ? "valid" : "not valid"), certFile.getAbsolutePath());
            boolean isActive = PersistHelper.getInstance().isActiveCertificate(getCertSubjectName(cert));
            return now >= notBefore && now <= notAfter && isActive;
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    Certificate getCertByFile(File certFile) throws KeyStoreException {
        try {
            return new X509CertImpl(new FileInputStream(certFile));
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    String getCertSubjectName(X509CertImpl cert) {
        return cert.getSubjectDN().toString().split(", ")[0].split("=")[1];

    }
}
