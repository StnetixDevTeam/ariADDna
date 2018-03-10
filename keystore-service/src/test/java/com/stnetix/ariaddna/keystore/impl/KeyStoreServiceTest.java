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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stnetix.ariaddna.keystore.IKeyStore;

/**
 * Created by alexkotov on 19.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KeyStoreTestConfig.class)
public class KeyStoreServiceTest {
    @Autowired
    private IKeyStore keyStore;

    @Test
    public void getCertByUUID() throws Exception {
        UUID uuid = UUID.randomUUID();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert, keyStoreFile);
        File searchedCert = keyStore.getCertByUUID(uuid, keyStoreFile);
        assertTrue(searchedCert.exists());
    }

    @Test
    public void removeCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert, keyStoreFile);
        keyStore.removeCert(cert, keyStoreFile);
        assertFalse(keyStore.isKeyStoreContainCert(cert, keyStoreFile));
    }

    @Test
    public void disableCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert, keyStoreFile);
        keyStore.disableCert(cert);
        assertFalse(keyStore.isValidCert(cert));
    }

    @Test
    public void isKeyStoreContainCert() throws Exception {
        UUID uuid = UUID.randomUUID();
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(uuid);
        keyStore.storeCert(cert, keyStoreFile);
        assertTrue(keyStore.isKeyStoreContainCert(cert, keyStoreFile));
    }

    @Test
    public void storeCert() throws Exception {
        File keyStoreFile = keyStore.generateKeyStore();
        File cert = keyStore.generateCert(UUID.randomUUID());
        keyStore.storeCert(cert, keyStoreFile);
        assertTrue(keyStore.isKeyStoreContainCert(cert, keyStoreFile));
    }

    @Test
    public void isValidCert() throws Exception {
        File cert = keyStore.generateCert(UUID.randomUUID());
        assertTrue(keyStore.isValidCert(cert));
    }

    @Test
    public void generateCert() throws Exception {
        File cert = keyStore.generateCert(UUID.randomUUID());
        System.out.println(cert.getAbsolutePath());
        assertNotNull(cert);

    }

    @Test
    public void generateKeyStore() throws Exception {
        File keyStoreFile = keyStore.generateKeyStore();
        System.out.println(keyStoreFile.getAbsolutePath());
        assertNotNull(keyStoreFile);
    }

}