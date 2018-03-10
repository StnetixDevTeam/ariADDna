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

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.stnetix.ariaddna.commonutils.dto.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.entities.AccessToken;

public class AccessTokenTransformerTest {
    AccessTokenTransformer transformer = Mappers.getMapper(AccessTokenTransformer.class);

    @Test
    public void accessTokenEntityToDTO() {
        AccessToken accessToken = new AccessToken();
        accessToken.setId(320L);
        accessToken.setTokenType("Bearer");
        accessToken.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
        accessToken.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        accessToken.setExpiresIn(10);

        AccessTokenDTO accessTokenDTO = transformer.accessTokenEntityToDTO(accessToken);

        Assert.assertNotEquals(accessTokenDTO, accessToken);
        Assert.assertEquals(accessTokenDTO.getId(), accessToken.getId());
        Assert.assertEquals(accessTokenDTO.getAccessToken(), accessToken.getAccessToken());
        Assert.assertEquals(accessTokenDTO.getRefreshToken(), accessToken.getRefreshToken());
        Assert.assertEquals(accessTokenDTO.getExpiresIn(), accessToken.getExpiresIn());
        Assert.assertEquals(accessTokenDTO.getTokenType(), accessToken.getTokenType());
    }

    @Test
    public void accessTokenDTOToEntityTest() {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setId(471L);
        accessTokenDTO.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
        accessTokenDTO.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        accessTokenDTO.setExpiresIn(10);
        accessTokenDTO.setTokenType("Bearer");

        AccessToken accessToken = transformer.accessTokenDTOToEntity(accessTokenDTO);

        Assert.assertNotEquals(accessToken, accessTokenDTO);
        Assert.assertEquals(accessToken.getId(), accessTokenDTO.getId());
        Assert.assertEquals(accessToken.getUuid(), accessTokenDTO.getUuid());
        Assert.assertEquals(accessToken.getAccessToken(), accessTokenDTO.getAccessToken());
        Assert.assertEquals(accessToken.getRefreshToken(), accessTokenDTO.getRefreshToken());
        Assert.assertEquals(accessToken.getExpiresIn(), accessTokenDTO.getExpiresIn());
        Assert.assertEquals(accessToken.getTokenType(), accessTokenDTO.getTokenType());
    }
}
