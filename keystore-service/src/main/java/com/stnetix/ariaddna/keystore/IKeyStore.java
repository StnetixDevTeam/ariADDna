package com.stnetix.ariaddna.keystore;

import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.keystore.impl.KeyStore;

import java.security.cert.Certificate;
import java.util.UUID;

/**
 * This interface define KeyStore service work.
 */
public interface IKeyStore {

    /**
     * This method generate KeyStore file on called side.
     * @return KeyStore object.
     * */
    KeyStore generateKeyStore() throws KeyStoreException;

    /**
     * This method generate {@link Certificate} by users UUID.
     * @return  generated Certificate.
     * */
    Certificate generateCert(UUID uuid) throws KeyStoreException;

    /**
     * This method store {@link Certificate} into local keystore file.
     * @param cert is Certificate to store.
     * */
    void storeCert(Certificate cert) throws KeyStoreException;

    /**
     * This method check is Certificate valid.
     * @return true if Certificate is valid.
     * */
    boolean isValidCert(Certificate cert) throws KeyStoreException;

    /**
     * This method check local KeyStore file contain the Certificate.
     * @return true if local KeyStore contain the Certificate.
     * */
    boolean isKeyStoreContainCert(Certificate cert) throws KeyStoreException;

    /**
     * This method find in local KeyStore Certificate where alias id UUID.
     * @return Certificate.
     * */
    Certificate getCertByUUID(UUID uuid) throws KeyStoreException;

    /**
     * This method remove Certificate from local KeyStore
     * */
    void removeCert(Certificate cert) throws KeyStoreException;

    /**
     * This method mark Certificate as disabled.
     * */
    void disableCert(Certificate cert) throws KeyStoreException;
}
