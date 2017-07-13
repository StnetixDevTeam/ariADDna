package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.entities.AccessToken;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.UUID;

public class AccessTokenTransformerTest {
    AccessTokenTransformer transformer = Mappers.getMapper(AccessTokenTransformer.class);

    @Test
    public void accessTokenEntityToDTO(){
        AccessToken accessToken = new AccessToken();
        accessToken.setId(320L);
        accessToken.setTokenType("Bearer");
        accessToken.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
        accessToken.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        accessToken.setExpiresIn(Integer.parseInt((new Date()).toString()));

        AccessTokenDTO accessTokenDTO = transformer.accessTokenEntityToDTO(accessToken);

        Assert.assertNotEquals(accessTokenDTO, accessToken);
        Assert.assertEquals(accessTokenDTO.getId(), accessToken.getId());
        Assert.assertEquals(accessTokenDTO.getAccessToken(), accessToken.getAccessToken());
        Assert.assertEquals(accessTokenDTO.getRefreshToken(), accessToken.getRefreshToken());
        Assert.assertEquals(accessTokenDTO.getExpiresIn(), accessToken.getExpiresIn());
        Assert.assertEquals(accessTokenDTO.getTokenType(), accessToken.getTokenType());
    }

    @Test
    public void accessTokenDTOToEntityTest(){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setId(471L);
        accessTokenDTO.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
        accessTokenDTO.setRefreshToken(UUID.randomUUID().toString().replace("-", ""));
        accessTokenDTO.setExpiresIn(Integer.parseInt((new Date()).toString()));
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
