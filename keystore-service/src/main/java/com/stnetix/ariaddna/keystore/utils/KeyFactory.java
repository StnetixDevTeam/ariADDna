package com.stnetix.ariaddna.keystore.utils;


import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import sun.security.x509.X509CertImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

/**
 * Created by alexkotov on 20.04.17.
 */
public class KeyFactory {
    private static final KeyFactory KEY_FACTORY = new KeyFactory();
    private KeyFactory(){}

    public static KeyFactory getKeyFactory() {
        return KEY_FACTORY;
    }

    private static final String KEYSTORE_PATH;
    private static final String DISABLED_KEYSTORE_PATH;
    static final char[] PASS;
    private static final AriaddnaLogger LOGGER;

    static {
        PASS = "password1".toCharArray();
        KEYSTORE_PATH = "ariaddna.keystore";
        DISABLED_KEYSTORE_PATH = "disabled_ariaddna.keystore";
        LOGGER = AriaddnaLogger.getLogger(KeyFactory.class);
    }

    public File getNewKeyStore() throws KeyStoreException {
        try  {
            return generateKeyStoreByName(KEYSTORE_PATH);
        } catch (Exception e) {
            LOGGER.error("KeyStore object is not create. Exception: ",e);
            throw new KeyStoreException("Caused by: ",e);
        }
    }

    public File getDisableKeyStore() throws KeyStoreException {
        try  {
            return generateKeyStoreByName(DISABLED_KEYSTORE_PATH);
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
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis,PASS);
            LOGGER.info("KeyStore load successful");
            fis.close();

            keyStore.setCertificateEntry(alias, cert);
            FileOutputStream fos = new FileOutputStream(keyStoreFile);
            keyStore.store(fos,PASS);
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
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis,PASS);
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
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis,PASS);
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
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis,PASS);
            fis.close();

            keyStore.deleteEntry(alias);

            FileOutputStream fos = new FileOutputStream(keyStoreFile);
            keyStore.store(fos,PASS);
            LOGGER.info("Certificate with filename {} deleted from keyStore with filename {}", certFile.getAbsolutePath(), keyStoreFile.getAbsolutePath());
            fos.close();

        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new KeyStoreException("Caused by: ", e);
        }
    }

    private File generateKeyStoreByName(String name) throws KeyStoreException {
        KeyStore keyStore = null;
        try (FileOutputStream fos = new FileOutputStream(name)) {
            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, PASS);
            keyStore.store(fos,PASS);
            File keyStoreFile = new File(name);
            LOGGER.info("KeyStore was create with file name {}", keyStoreFile.getAbsolutePath());
            return keyStoreFile;
        } catch (Exception e) {
            LOGGER.error("KeyStore object is not create. Exception: ",e);
            throw new KeyStoreException("Caused by: ",e);
        }

    }


}
