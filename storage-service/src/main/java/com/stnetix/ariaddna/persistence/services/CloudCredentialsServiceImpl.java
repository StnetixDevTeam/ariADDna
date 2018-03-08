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

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.entities.CloudCredentials;
import com.stnetix.ariaddna.persistence.repositories.CloudCredentialsRepository;
import com.stnetix.ariaddna.persistence.transformers.CloudCredentialsTransformer;

@Repository
@Transactional(readOnly = true)
public class CloudCredentialsServiceImpl implements ICloudCredentialsService {

    private CloudCredentialsTransformer transformer;

    @Autowired
    private CloudCredentialsRepository repository;

    public CloudCredentialsServiceImpl() {
        transformer = Mappers.getMapper(CloudCredentialsTransformer.class);
    }

    @Override
    public CloudCredentialsDTO saveCloudCredentials(CloudCredentialsDTO cloudCredentialsDTO) {
        CloudCredentials cloudCredentials = transformer
                .cloudCredentialsDTOToEntity(cloudCredentialsDTO);
        return transformer.cloudCredentialsEntityToDTO(repository.save(cloudCredentials));
    }

    @Override
    public CloudCredentialsDTO getCloudCredentials() {
        List<CloudCredentialsDTO> cloudCredentialsDTOList = getAllCloudCredentials();
        if (cloudCredentialsDTOList.isEmpty()) {
            CloudCredentialsDTO newCloudCredentialsDTO = new CloudCredentialsDTO();
            return saveCloudCredentials(newCloudCredentialsDTO);
        }
        return cloudCredentialsDTOList.get(0);
    }

    private List<CloudCredentialsDTO> getAllCloudCredentials() {
        List<CloudCredentialsDTO> cloudCredentialsDTOList = new ArrayList<>();
        repository.findAll().forEach(
                cloudCredentials -> cloudCredentialsDTOList
                        .add(transformer.cloudCredentialsEntityToDTO(cloudCredentials)));
        return cloudCredentialsDTOList;
    }
}
