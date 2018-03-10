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

package com.stnetix.ariaddna.commonutils.dto;

import java.util.UUID;

/**
 * IMPORTANT: Client ID and Client Secret are very important parameters and MUST be kept in secret.
 *
 * To authorize ariADDna act on behalf of a user at his cloud storage (DropBox, Yandex.Disk, etc)
 * a developer should register the ariADDna app at a target cloud storage. After this
 * he will acquire two parameters: Client ID and Client Secret for authentication.
 * One should think of this parameters as a login-password pair which is used only once
 * when a user is going to connect his cloud storage account to ariADDna.
 *
 * Before the app will be authorized, the authentication must be passed.
 * It's aim is to tell a cloud storage approximately the following:
 * "Hey, DropBox, I am ariADDna. Here are my ID and Secret,
 * give me an OAuth token to act on behalf of this user. He allowed me to do so."
 * And as a result ariADDna will receive the OAuth token or, in other words, will be authorized.
 *
 * USAGE:
 * When a necessity to connect a new user's cloud storage account occurs
 * a Client ID and a Client Secret must be used. They must be stored in the DataBase,
 * but before storing they should be placed there.
 *
 * Client ID and Client Secret can be retrieved ONLY manually by developer at the web-page
 * where the ariADDna was registered. After retrieving both parameters should be written
 * into a Property-file. During the following launch of ariADDna parameters will be extracted
 * and an instance of the CloudCredentialsDTO class must be created. It's fields must be
 * filled with the extracted parameters. After that a developer MUST delete the entries from
 * the Property-file and sent CloudCredentialsDTO to a DataBase converting it into the Entity (CloudCredentials
 * class instance), transforming it and so on.
 *
 * For authentication of ariADDna Client ID and a Client Secret should be extracted from a DB
 * and converted to CloudCredentialDTO.
 */
public class CloudCredentialsDTO {

    public CloudCredentialsDTO() {
        this.uuid = UUID.randomUUID().toString();
    }

    public CloudCredentialsDTO(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
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

    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    private String clientSecret;

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
