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

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
//Transactional mark this test to rollback all changes after test
@Transactional
public class AccessTokenServiceImplTest {
    @Autowired
    private IAccessTokenService accessTokenService;

    @Test
    public void saveAccessTokenTest() {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        UUID uuid = UUID.randomUUID();
        accessTokenDTO.setUuid(uuid.toString());

        AccessTokenDTO savedAccessToken = accessTokenService.saveToken(accessTokenDTO);
        Assert.assertNotNull(savedAccessToken);
        Assert.assertEquals(accessTokenDTO.getUuid(), savedAccessToken.getUuid());
    }
}
