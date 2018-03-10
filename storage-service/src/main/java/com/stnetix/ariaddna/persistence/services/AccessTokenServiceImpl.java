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


import com.stnetix.ariaddna.commonutils.dto.AccessTokenDTO;
import com.stnetix.ariaddna.persistence.entities.AccessToken;
import com.stnetix.ariaddna.persistence.repositories.AccessTokenRepository;
import com.stnetix.ariaddna.persistence.transformers.AccessTokenTransformer;

@Repository
@Transactional(readOnly = true)
public class AccessTokenServiceImpl implements IAccessTokenService {

    private AccessTokenTransformer transformer;

    @Autowired
    private AccessTokenRepository repository;

    public AccessTokenServiceImpl() {
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
        if (accessTokenDTOList.size() == 0) {
            AccessTokenDTO newAccessTokenDTO = new AccessTokenDTO();
            return saveToken(newAccessTokenDTO);
        }
        return accessTokenDTOList.get(0);
    }

    private List<AccessTokenDTO> getAllAccessTokens() {
        List<AccessTokenDTO> accessTokenDTOList = new ArrayList<>();
        repository.findAll().forEach(
                accessToken -> accessTokenDTOList
                        .add(transformer.accessTokenEntityToDTO(accessToken)));
        return accessTokenDTOList;
    }
}
