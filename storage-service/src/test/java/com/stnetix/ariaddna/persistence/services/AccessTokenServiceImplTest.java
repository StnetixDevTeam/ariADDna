package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

public class AccessTokenServiceImplTest {
    private ConfigurableApplicationContext context;
    private AccessTokenServiceImpl accessTokenService;

    @Before
    public void before() {
        context = SpringApplication.run(AppConfiguration.class);
        accessTokenService = context.getBean(AccessTokenServiceImpl.class);
    }

    @Test
    public void saveAccessTokenTest(){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        UUID uuid = UUID.randomUUID();
        accessTokenDTO.setUuid(uuid.toString());

        AccessTokenDTO savedAccessToken = accessTokenService.saveToken(accessTokenDTO);
        Assert.assertNotNull(savedAccessToken);
        Assert.assertEquals(accessTokenDTO.getUuid(), savedAccessToken.getUuid());
    }
}
