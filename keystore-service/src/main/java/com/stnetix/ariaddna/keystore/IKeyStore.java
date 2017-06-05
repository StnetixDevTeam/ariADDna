package com.stnetix.ariaddna.keystore;

import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;


import java.io.File;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.UUID;

/**
 * This interface define KeyStore service work.
 */
public interface IKeyStore {

    /**
     * This method generate file with keystore.
     * @return File of keystore.
     * */
    File generateKeyStore() throws KeyStoreException;

    /**
     * This method generate certificate {@link File} by users UUID.
     * @return  generated certificate file.
     * */
    File generateCert(UUID uuid) throws KeyStoreException;

    /**
     * This method store certificate {@link File} into keystore file.
     * @param certFile is certificate file to store.
     * @param keyStore is keystore file where certificate was stored.
     * */
    void storeCert(File certFile, File keyStore) throws KeyStoreException;

    /**
     * This method check is Certificate valid.
     * @param certFile is certificate file.
     * @return true if Certificate is valid.
     * */
    boolean isValidCert(File certFile) throws KeyStoreException;

    /**
     * This method check KeyStore file contain the Certificate file.
     * @param certFile is certificate file.
     * @param keyStore is keystore file where certificate was stored.
     * @return true if KeyStore contain the certificate.
     * */
    boolean isKeyStoreContainCert(File certFile, File keyStore) throws KeyStoreException;

    /**
     * This method find in local KeyStore Certificate where alias id UUID.
     * @param uuid
     * @param keyStore is keystore file.
     * @return certificate file.
     * */
    File getCertByUUID(UUID uuid, File keyStore) throws KeyStoreException;

    /**
     * This method remove Certificate from KeyStore
     * @param certFile is certificate file.
     * @param keyStoreFrom is keystore file where certificate was deleted.
     * */
    void removeCert(File certFile, File keyStoreFrom) throws KeyStoreException;

    /**
     * This method mark Certificate as disabled.
     * @param certFile is certificate file.
     * */
    void disableCert(File certFile) throws KeyStoreException;
}
