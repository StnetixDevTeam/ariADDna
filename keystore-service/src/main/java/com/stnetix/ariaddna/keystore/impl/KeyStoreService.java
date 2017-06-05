package com.stnetix.ariaddna.keystore.impl;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.keystore.utils.CertFactory;
import com.stnetix.ariaddna.keystore.utils.KeyFactory;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.UUID;

/**
 * Its implement IKeyStore
 */
@Component
public class KeyStoreService implements IKeyStore {


    @Override
    public File generateKeyStore() throws KeyStoreException {
        return KeyFactory.getKeyFactory().getNewKeyStore();
    }

    @Override
    public File generateCert(UUID uuid) throws KeyStoreException {
        return CertFactory.getCertFactory().getNewCertificate(uuid.toString());
    }

    @Override
    public void storeCert(File certFile, File keystore) throws KeyStoreException {
        KeyFactory.getKeyFactory().storeCertToKeyStore(certFile, keystore);
    }

    @Override
    public boolean isValidCert(File certFile) throws KeyStoreException {
        return CertFactory.getCertFactory().isValid(certFile);
    }

    @Override
    public boolean isKeyStoreContainCert(File certFile, File keystore) throws KeyStoreException {
        return KeyFactory.getKeyFactory().isCertContainsInKeyStore(certFile, keystore);
    }

    @Override
    public File getCertByUUID(UUID uuid, File keystore) throws KeyStoreException {
        return KeyFactory.getKeyFactory().getCertByAlias(uuid.toString(), keystore);
    }

    @Override
    public void removeCert(File certFile, File keystore) throws KeyStoreException {
        KeyFactory.getKeyFactory().removeCertFromKeyStore(certFile, keystore);
    }

    @Override
    public void disableCert(File certFile) throws KeyStoreException {
        KeyFactory.getKeyFactory().setCertDisable(certFile);

    }
}
