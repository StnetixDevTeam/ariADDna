package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.entities.CloudCredentials;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class CloudCredentialsTransformerTest {
    CloudCredentialsTransformer transformer = Mappers.getMapper(CloudCredentialsTransformer.class);

    @Test
    public void cloudCredentialsEntityToDTO(){
        CloudCredentials cloudCredentials = new CloudCredentials();
        cloudCredentials.setId(423L);
        cloudCredentials.setClientId(UUID.randomUUID().toString());
        cloudCredentials.setClientSecret(UUID.randomUUID().toString());

        CloudCredentialsDTO cloudCredentialsDTO = transformer.cloudCredentialsEntityToDTO(cloudCredentials);

        Assert.assertNotEquals(cloudCredentialsDTO, cloudCredentials);
        Assert.assertEquals(cloudCredentialsDTO.getId(), cloudCredentials.getId());
        Assert.assertEquals(cloudCredentialsDTO.getClientId(), cloudCredentials.getClientId());
        Assert.assertEquals(cloudCredentialsDTO.getClientSecret(), cloudCredentials.getClientSecret());
    }

    @Test
    public void cloudCredentialsDTOToEntity(){
        CloudCredentialsDTO cloudCredentialsDTO = new CloudCredentialsDTO();
        cloudCredentialsDTO.setId(221L);
        cloudCredentialsDTO.setClientId(UUID.randomUUID().toString());
        cloudCredentialsDTO.setClientSecret(UUID.randomUUID().toString());

        CloudCredentials cloudCredentials = transformer.cloudCredentialsDTOToEntity(cloudCredentialsDTO);

        Assert.assertNotEquals(cloudCredentials, cloudCredentialsDTO);
        Assert.assertEquals(cloudCredentials.getId(), cloudCredentialsDTO.getId());
        Assert.assertEquals(cloudCredentials.getUuid(), cloudCredentialsDTO.getUuid());
        Assert.assertEquals(cloudCredentials.getClientId(), cloudCredentialsDTO.getClientId());
        Assert.assertEquals(cloudCredentials.getClientSecret(), cloudCredentialsDTO.getClientSecret());
    }
}
