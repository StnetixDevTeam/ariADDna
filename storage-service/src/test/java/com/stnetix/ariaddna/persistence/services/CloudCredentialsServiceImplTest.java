package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
//Transactional mark this test to rollback all changes after test
@Transactional
public class CloudCredentialsServiceImplTest {
    @Autowired
    private ICloudCredentialsService cloudCredentialsService;

    @Test
    public void saveCloudCredentials(){
        CloudCredentialsDTO cloudCredentialsDTO = new CloudCredentialsDTO();
        UUID uuid = UUID.randomUUID();
        cloudCredentialsDTO.setUuid(uuid.toString());

        CloudCredentialsDTO savedCloudCredentials =
                cloudCredentialsService.saveCloudCredentials(cloudCredentialsDTO);
        Assert.assertNotNull(savedCloudCredentials);
        Assert.assertEquals(cloudCredentialsDTO.getUuid(), savedCloudCredentials.getUuid());
    }
}
