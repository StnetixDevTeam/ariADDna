package com.stnetix.ariaddna.externalcloudapi;

import com.google.gson.JsonObject;

public class AccessToken {
    private String tokenType;

    private String accessToken;

    private Integer expiresIn;

    private String refreshToken;

    private AccessToken(String tokenType, String accessToken, Integer expiresIn, String refreshToken){
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    public static AccessToken buildAccessToken(JsonObject authResponse){

        return new AccessToken(
                        authResponse.get("token_type").toString(),
                        authResponse.get("access_token").toString(),
                        Integer.parseInt(authResponse.get("expires_in").toString()),
                        authResponse.get("refresh_token").toString()
                );
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
