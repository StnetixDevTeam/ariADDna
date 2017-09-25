package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.entities.CloudCredentials;
import org.mapstruct.Mapper;

@Mapper
public interface CloudCredentialsTransformer {
    CloudCredentialsDTO cloudCredentialsEntityToDTO(CloudCredentials cloudCredentials);
    CloudCredentials cloudCredentialsDTOToEntity(CloudCredentialsDTO cloudCredentialsDTO);
}
