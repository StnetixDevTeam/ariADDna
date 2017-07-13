package com.stnetix.ariaddna.commonutils.DTO;

import java.util.UUID;

/**
 * An instance of this class is the key element for authorization mechanism via OAuth.
 * A developer should use it when working with external cloud storages and with our inner services.
 */
public class AccessTokenDTO {
    public AccessTokenDTO() {
        this.uuid = UUID.randomUUID().toString();
    }

    public AccessTokenDTO(String tokenType, String accessToken){
        this();
        this.tokenType = tokenType;
        this.accessToken =  accessToken;
    }

    public AccessTokenDTO(String tokenType, String accessToken, Integer expiresIn, String refreshToken) {
        this();
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private Integer expiresIn;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}
