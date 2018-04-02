/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
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

package com.stnetix.ariaddna.externalcloudapi;

import com.google.gson.JsonObject;

public class AccessToken {
    private String tokenType;

    private String accessToken;

    private Integer expiresIn;

    private String refreshToken;

    private AccessToken(String tokenType, String accessToken, Integer expiresIn,
            String refreshToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    public static AccessToken buildAccessToken(JsonObject authResponse) {
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
