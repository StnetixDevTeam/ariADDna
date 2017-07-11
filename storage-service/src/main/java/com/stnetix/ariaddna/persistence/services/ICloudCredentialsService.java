package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;

public interface ICloudCredentialsService {
    CloudCredentialsDTO saveCloudCredentials(CloudCredentialsDTO cloudCredentials);
    CloudCredentialsDTO getCloudCredentials();
}
