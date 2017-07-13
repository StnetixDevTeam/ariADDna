package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.entities.AccessToken;
import com.stnetix.ariaddna.persistence.repositories.AccessTokenRepository;
import com.stnetix.ariaddna.persistence.transformers.AccessTokenTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AccessTokenServiceImpl implements IAccessTokenService {

    private AccessTokenTransformer transformer;

    @Autowired
    private AccessTokenRepository repository;

    public AccessTokenServiceImpl(){
        transformer = Mappers.getMapper(AccessTokenTransformer.class);
    }

    @Override
    public AccessTokenDTO saveToken(AccessTokenDTO accessTokenDTO) {
        AccessToken accessToken = transformer.accessTokenDTOToEntity(accessTokenDTO);
        return transformer.accessTokenEntityToDTO(repository.save(accessToken));
    }

    @Override
    public AccessTokenDTO getToken() {
        List<AccessTokenDTO> accessTokenDTOList = getAllAccessTokens();
        if(accessTokenDTOList.size()==0){
            AccessTokenDTO newAccessTokenDTO = new AccessTokenDTO();
            return saveToken(newAccessTokenDTO);
        }
        return accessTokenDTOList.get(0);
    }

    private List<AccessTokenDTO> getAllAccessTokens(){
        List<AccessTokenDTO> accessTokenDTOList = new ArrayList<>();
        repository.findAll().forEach(
                accessToken -> accessTokenDTOList.add(transformer.accessTokenEntityToDTO(accessToken)));
        return accessTokenDTOList;
    }
}
