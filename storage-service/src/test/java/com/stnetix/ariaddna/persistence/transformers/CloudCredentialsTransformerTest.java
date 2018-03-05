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

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.entities.CloudCredentials;

public class CloudCredentialsTransformerTest {
    CloudCredentialsTransformer transformer = Mappers.getMapper(CloudCredentialsTransformer.class);

    @Test
    public void cloudCredentialsEntityToDTO() {
        CloudCredentials cloudCredentials = new CloudCredentials();
        cloudCredentials.setId(423L);
        cloudCredentials.setClientId(UUID.randomUUID().toString());
        cloudCredentials.setClientSecret(UUID.randomUUID().toString());

        CloudCredentialsDTO cloudCredentialsDTO = transformer
                .cloudCredentialsEntityToDTO(cloudCredentials);

        Assert.assertNotEquals(cloudCredentialsDTO, cloudCredentials);
        Assert.assertEquals(cloudCredentialsDTO.getId(), cloudCredentials.getId());
        Assert.assertEquals(cloudCredentialsDTO.getClientId(), cloudCredentials.getClientId());
        Assert.assertEquals(cloudCredentialsDTO.getClientSecret(),
                cloudCredentials.getClientSecret());
    }

    @Test
    public void cloudCredentialsDTOToEntity() {
        CloudCredentialsDTO cloudCredentialsDTO = new CloudCredentialsDTO();
        cloudCredentialsDTO.setId(221L);
        cloudCredentialsDTO.setClientId(UUID.randomUUID().toString());
        cloudCredentialsDTO.setClientSecret(UUID.randomUUID().toString());

        CloudCredentials cloudCredentials = transformer
                .cloudCredentialsDTOToEntity(cloudCredentialsDTO);

        Assert.assertNotEquals(cloudCredentials, cloudCredentialsDTO);
        Assert.assertEquals(cloudCredentials.getId(), cloudCredentialsDTO.getId());
        Assert.assertEquals(cloudCredentials.getUuid(), cloudCredentialsDTO.getUuid());
        Assert.assertEquals(cloudCredentials.getClientId(), cloudCredentialsDTO.getClientId());
        Assert.assertEquals(cloudCredentials.getClientSecret(),
                cloudCredentialsDTO.getClientSecret());
    }
}
