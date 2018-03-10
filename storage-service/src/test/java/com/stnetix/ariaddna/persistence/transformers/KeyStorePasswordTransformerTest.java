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

package com.stnetix.ariaddna.persistence.transformers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.stnetix.ariaddna.commonutils.dto.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;

/**
 * Created by alexkotov on 03.05.17.
 */
public class KeyStorePasswordTransformerTest {
    KeyStorePasswordTransformer transformer = Mappers.getMapper(KeyStorePasswordTransformer.class);

    @Test
    public void keyStorePasswordEntityToDTO() throws Exception {
        KeyStorePassword keyStorePassword = new KeyStorePassword();
        keyStorePassword.setId(2L);
        keyStorePassword.setPass("1234567890");
        KeyStorePasswordDTO keyStorePasswordDTO = transformer
                .keyStorePasswordEntityToDTO(keyStorePassword);

        assertNotEquals(keyStorePasswordDTO, keyStorePassword);
        assertEquals(keyStorePasswordDTO.getId(), keyStorePassword.getId());
        assertEquals(keyStorePasswordDTO.getPass(), keyStorePassword.getPass());
    }

    @Test
    public void keyStorePasswordDTOToEntity() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO = new KeyStorePasswordDTO("1234567890");
        keyStorePasswordDTO.setId(2L);
        KeyStorePassword keyStorePassword = transformer
                .keyStorePasswordDTOToEntity(keyStorePasswordDTO);

        assertNotEquals(keyStorePassword, keyStorePasswordDTO);
        assertEquals(keyStorePassword.getId(), keyStorePasswordDTO.getId());
        assertEquals(keyStorePassword.getPass(), keyStorePasswordDTO.getPass());
    }

}