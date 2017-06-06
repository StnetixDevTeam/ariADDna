package com.stnetix.ariaddna.keystore.impl;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.keystore.utils.CertFactory;
import com.stnetix.ariaddna.keystore.utils.KeyFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.util.UUID;

/**
 * Its implement IKeyStore
 */
public class KeyStoreService implements IKeyStore {
    @Autowired
    private KeyFactory keyFactory;

    @Autowired
    private CertFactory certFactory;

    @Override
    public File generateKeyStore() throws KeyStoreException {
        return keyFactory.getNewKeyStore();
    }

    @Override
    public File generateCert(UUID uuid) throws KeyStoreException {
        return certFactory.getNewCertificate(uuid.toString());
    }

    @Override
    public void storeCert(File certFile, File keystore) throws KeyStoreException {
        keyFactory.storeCertToKeyStore(certFile, keystore);
    }

    @Override
    public boolean isValidCert(File certFile) throws KeyStoreException {
        return certFactory.isValid(certFile);
    }

    @Override
    public boolean isKeyStoreContainCert(File certFile, File keystore) throws KeyStoreException {
        return keyFactory.isCertContainsInKeyStore(certFile, keystore);
    }

    @Override
    public File getCertByUUID(UUID uuid, File keystore) throws KeyStoreException {
        return keyFactory.getCertByAlias(uuid.toString(), keystore);
    }

    @Override
    public void removeCert(File certFile, File keystore) throws KeyStoreException {
        keyFactory.removeCertFromKeyStore(certFile, keystore);
    }

    @Override
    public void disableCert(File certFile) throws KeyStoreException {
        keyFactory.setCertDisable(certFile);

    }
}
