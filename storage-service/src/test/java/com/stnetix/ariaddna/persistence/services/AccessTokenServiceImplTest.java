package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
//Transactional mark this test to rollback all changes after test
@Transactional
public class AccessTokenServiceImplTest {
    @Autowired
    private IAccessTokenService accessTokenService;

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
