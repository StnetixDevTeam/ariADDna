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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.entities.UserEntity;

/**
 * Created by alexkotov on 13.09.17.
 */
public class UserTransformerTest {
    private UserTransformer transformer = Mappers.getMapper(UserTransformer.class);

    @Test
    public void userDTOToEntity() throws Exception {
        UserDTO userDTO = TestHelper.getUserDTO();

        UserEntity userEntity = transformer.userDTOToEntity(userDTO);
        assertNotNull(userEntity);
        assertEquals(userEntity.getUuid(), userDTO.getUuid());
        assertEquals(userEntity.getNickname(), userDTO.getNickname());
    }

    @Test
    public void userEntityToDTO() throws Exception {
        UserEntity userEntity = TestHelper.getUserEntity();

        UserDTO userDTO = transformer.userEntityToDTO(userEntity);
        assertNotNull(userDTO);
        assertEquals(userDTO.getNickname(), userEntity.getNickname());
        assertEquals(userDTO.getUuid(), userEntity.getUuid());
    }

}