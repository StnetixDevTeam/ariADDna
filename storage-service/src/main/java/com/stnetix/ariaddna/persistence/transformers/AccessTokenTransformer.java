package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.entities.AccessToken;
import org.mapstruct.Mapper;

@Mapper
public interface AccessTokenTransformer {
    AccessTokenDTO accessTokenEntityToDTO(AccessToken accessToken);
    AccessToken accessTokenDTOToEntity(AccessTokenDTO accessTokenDTO);
}
