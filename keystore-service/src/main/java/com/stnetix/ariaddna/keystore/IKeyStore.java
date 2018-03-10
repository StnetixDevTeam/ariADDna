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

package com.stnetix.ariaddna.keystore;

import java.io.File;
import java.util.UUID;

import com.stnetix.ariaddna.keystore.exceptions.KeyStoreException;

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
     * @return generated certificate file.
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
