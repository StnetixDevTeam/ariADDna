/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.keystore.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;
import com.stnetix.ariaddna.keystore.utils.CertFactory;
import com.stnetix.ariaddna.keystore.utils.KeyFactory;

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
