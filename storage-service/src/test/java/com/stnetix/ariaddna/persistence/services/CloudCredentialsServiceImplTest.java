package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

public class CloudCredentialsServiceImplTest {
    private ConfigurableApplicationContext context;
    private CloudCredentialsServiceImpl cloudCredentialsService;

    @Before
    public void before() {
        context = SpringApplication.run(AppConfiguration.class);
        cloudCredentialsService = context.getBean(CloudCredentialsServiceImpl.class);
    }

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
