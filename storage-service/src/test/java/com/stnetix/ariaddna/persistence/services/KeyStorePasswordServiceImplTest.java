/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
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

package com.stnetix.ariaddna.persistence.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;

/**
 * Created by alexkotov on 11.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
@Transactional
@SpringBootTest
public class KeyStorePasswordServiceImplTest {

    @Autowired
    private IKeyStorePasswordService keyStorePasswordService;

    @Test
    public void saveTest() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO1 = new KeyStorePasswordDTO("0123456789");
        KeyStorePasswordDTO keyStorePasswordDTO2 = new KeyStorePasswordDTO("9876543210");
        KeyStorePasswordDTO savedKeyStorePasswordDTO1 = keyStorePasswordService
                .save(keyStorePasswordDTO1);
        KeyStorePasswordDTO savedKeyStorePasswordDTO2 = keyStorePasswordService
                .save(keyStorePasswordDTO2);

        assertEquals(savedKeyStorePasswordDTO1.getPass(), savedKeyStorePasswordDTO2.getPass());

    }

    @Test
    public void getPasswordTest() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO1 = new KeyStorePasswordDTO("0123456789");
        KeyStorePasswordDTO keyStorePasswordDTO2 = new KeyStorePasswordDTO("9876543210");
        KeyStorePasswordDTO savedKeyStorePasswordDTO1 = keyStorePasswordService
                .save(keyStorePasswordDTO1);
        KeyStorePasswordDTO savedKeyStorePasswordDTO2 = keyStorePasswordService
                .save(keyStorePasswordDTO2);
        KeyStorePasswordDTO keyStorePasswordDTO = keyStorePasswordService.getPassword();

        assertEquals(keyStorePasswordDTO.getPass(), savedKeyStorePasswordDTO1.getPass());
        assertEquals(keyStorePasswordDTO.getPass(), savedKeyStorePasswordDTO2.getPass());

    }

}