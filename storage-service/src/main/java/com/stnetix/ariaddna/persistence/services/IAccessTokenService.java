package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.AccessTokenDTO;

public interface IAccessTokenService {
    AccessTokenDTO saveToken(AccessTokenDTO accessToken);
    AccessTokenDTO getToken();
}
