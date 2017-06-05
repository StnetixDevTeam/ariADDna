package com.stnetix.ariaddna.keystore.utils;


import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.x509.X509CertImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

/**
 * Created by alexkotov on 20.04.17.
 */
@Component
public class KeyFactory {
    private static final KeyFactory KEY_FACTORY = new KeyFactory();
    private KeyFactory(){
        pass = PersistHelper.getInstance().getPassword();
    }

    public static KeyFactory getKeyFactory() {
        return KEY_FACTORY;
    }

    private static final String KEYSTORE_PATH;
    private char[] pass;
    private static final AriaddnaLogger LOGGER;
    private static final String KEYSTORE_FORMAT;

    static {

        KEYSTORE_PATH = "ariaddna.keystore";
        LOGGER = AriaddnaLogger.getLogger(KeyFactory.class);
        KEYSTORE_FORMAT = "JKS";
    }

    public File getNewKeyStore() throws KeyStoreException {
        try  {
            return generateKeyStoreByName(KEYSTORE_PATH);
        } catch (Exception e) {
            LOGGER.error("KeyStore object is not create. Exception: ",e);
            throw new KeyStoreException("Caused by: ",e);
        }
    }

    public void storeCertToKeyStore(File certFile, File keyStoreFile) throws KeyStoreException {
        try {
            X509CertImpl cert = (X509CertImpl) CertFactory.getCertFactory().getCertByFile(certFile);
            String alias = CertFactory.getCertFactory().getCertSubjectName(cert);
            LOGGER.info("Certificate with filename {} has Subject name {}", certFile.getAbsolutePath(), alias);
            FileInputStream fis = new FileInputStream(keyStoreFile);
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_FORMAT);
            keyStore.load(fis, pass);
            LOGGER.info("KeyStore load successful");
            fis.close();

            keyStore.setCertificateEntry(alias, cert);
            FileOutputStream fos = new FileOutputStream(keyStoreFile);
            keyStore.store(fos, pass);
            LOGGER.info("Certificate with filename {} stored in keyStore with filename {}", certFile.getAbsolutePath(), keyStoreFile.getAbsolutePath());
            fos.close();

        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    public boolean isCertContainsInKeyStore(File certFile, File keyStoreFile) throws KeyStoreException {
        try (FileInputStream fis = new FileInputStream(keyStoreFile)) {
            X509CertImpl cert = (X509CertImpl) CertFactory.getCertFactory().getCertByFile(certFile);
            String alias = CertFactory.getCertFactory().getCertSubjectName(cert);
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_FORMAT);
            keyStore.load(fis, pass);
            LOGGER.info("Certificate with filename {} "+(keyStore.containsAlias(alias)?"contain":"not contain")+" in keystore with filename {}", certFile.getAbsolutePath(), keyStoreFile.getAbsolutePath());
            return keyStore.containsAlias(alias);

        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    public File getCertByAlias(String alias, File keyStoreFile) throws KeyStoreException {
        try {
            FileInputStream fis = new FileInputStream(keyStoreFile);
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_FORMAT);
            keyStore.load(fis, pass);
            LOGGER.info("KeyStore {} loaded successful.", keyStoreFile.getAbsolutePath());
            fis.close();

            X509CertImpl cert = (X509CertImpl) keyStore.getCertificate(alias);
            File certFile = new File(alias+".cer");
            FileOutputStream fos = new FileOutputStream(certFile);
            fos.write(cert.getEncoded());
            LOGGER.info("Certificate {} loaded successful.", certFile.getAbsolutePath());
            fos.close();
            return certFile;
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    public void removeCertFromKeyStore(File certFile, File keyStoreFile) throws KeyStoreException {
        try {
            X509CertImpl cert = (X509CertImpl) CertFactory.getCertFactory().getCertByFile(certFile);
            String alias = CertFactory.getCertFactory().getCertSubjectName(cert);

            FileInputStream fis = new FileInputStream(keyStoreFile);
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_FORMAT);
            keyStore.load(fis, pass);
            fis.close();

            keyStore.deleteEntry(alias);

            FileOutputStream fos = new FileOutputStream(keyStoreFile);
            keyStore.store(fos, pass);
            LOGGER.info("Certificate with filename {} deleted from keyStore with filename {}", certFile.getAbsolutePath(), keyStoreFile.getAbsolutePath());
            fos.close();
            PersistHelper.getInstance().deleteCertificate(alias);

        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    public void setCertDisable(File certFile) throws KeyStoreException {
        X509CertImpl cert = (X509CertImpl) CertFactory.getCertFactory().getCertByFile(certFile);
        String alias = CertFactory.getCertFactory().getCertSubjectName(cert);
        PersistHelper.getInstance().setCertificateDisable(alias);
    }

    private File generateKeyStoreByName(String name) throws KeyStoreException {
        KeyStore keyStore = null;
        try (FileOutputStream fos = new FileOutputStream(name)) {
            keyStore = KeyStore.getInstance(KEYSTORE_FORMAT);
            keyStore.load(null, pass);
            keyStore.store(fos, pass);
            File keyStoreFile = new File(name);
            LOGGER.info("KeyStore was create with file name {}", keyStoreFile.getAbsolutePath());
            return keyStoreFile;
        } catch (Exception e) {
            LOGGER.error("KeyStore object is not create. Exception: ",e);
            throw new KeyStoreException("Caused by: ",e);
        }

    }


}
