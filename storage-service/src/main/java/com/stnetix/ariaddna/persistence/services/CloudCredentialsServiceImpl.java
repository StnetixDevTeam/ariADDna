package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.CloudCredentialsDTO;
import com.stnetix.ariaddna.persistence.entities.CloudCredentials;
import com.stnetix.ariaddna.persistence.repositories.CloudCredentialsRepository;
import com.stnetix.ariaddna.persistence.transformers.CloudCredentialsTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CloudCredentialsServiceImpl implements ICloudCredentialsService {

    private CloudCredentialsTransformer transformer;

    @Autowired
    private CloudCredentialsRepository repository;

    public CloudCredentialsServiceImpl(){
        transformer = Mappers.getMapper(CloudCredentialsTransformer.class);
    }

    @Override
    public CloudCredentialsDTO saveCloudCredentials(CloudCredentialsDTO cloudCredentialsDTO) {
        CloudCredentials cloudCredentials = transformer.cloudCredentialsDTOToEntity(cloudCredentialsDTO);
        return transformer.cloudCredentialsEntityToDTO(repository.save(cloudCredentials));
    }

    @Override
    public CloudCredentialsDTO getCloudCredentials() {
        List<CloudCredentialsDTO> cloudCredentialsDTOList = getAllCloudCredentials();
        if(cloudCredentialsDTOList.isEmpty()){
            CloudCredentialsDTO newCloudCredentialsDTO = new CloudCredentialsDTO();
            return saveCloudCredentials(newCloudCredentialsDTO);
        }
        return cloudCredentialsDTOList.get(0);
    }

    private List<CloudCredentialsDTO> getAllCloudCredentials(){
        List<CloudCredentialsDTO> cloudCredentialsDTOList = new ArrayList<>();
        repository.findAll().forEach(
                cloudCredentials -> cloudCredentialsDTOList.add(transformer.cloudCredentialsEntityToDTO(cloudCredentials)));
        return cloudCredentialsDTOList;
    }
}
