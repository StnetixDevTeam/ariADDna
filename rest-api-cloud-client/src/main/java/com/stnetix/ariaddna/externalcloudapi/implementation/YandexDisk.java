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

package com.stnetix.ariaddna.externalcloudapi.implementation;

import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.APP_ROOT;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.CLIENT_ID;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.CLIENT_SECRET;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.COPY_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.DISK_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.DLOAD_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.MOVE_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.RESOURCES_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.TOKEN_REQ_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.ULOAD_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.VCODE_REQ_PATH;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.deleteRequest;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.getFileFromCloud;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.getRequest;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.postRequest;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.putRequest;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.sendFileToCloud;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.sendRequest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;

import com.stnetix.ariaddna.externalcloudapi.AccessToken;
import com.stnetix.ariaddna.externalcloudapi.MediaTypes;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.IAbstractCloud;

//TODO Выделить в отдельный модуль все хосты и огрызки URL'ов
//TODO Отправлять в БД настройки облака
//TODO Продумать способ хранения множества аккаунтов

public class YandexDisk implements IAbstractCloud {

    private Request request;

    private JsonObject result;

    private OkHttpClient client;

    private AccessToken accessToken;

    private String verificationCode = "3650961";

    private String tempAccessToken = "AQAAAAAeFNwNAARF-Q0jtdjwhUH6mR6qL3eeGhg";

    public YandexDisk() {
        client = new OkHttpClient();
    }

    public YandexDisk(OkHttpClient client) {
        this.client = client;
    }

    private void openOAuthPage() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(VCODE_REQ_PATH.uri());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(String value) {
        this.verificationCode = value;
    }

    @Override
    public JsonObject uploadFile(File path) {
        HttpUrl uploadPath = ULOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getPath()).build();

        request = getRequest(uploadPath, tempAccessToken);
        result = sendRequest(client, request);
        String href = result.get("href").toString();
        href = href.substring(1, href.length() - 1);
        sendFileToCloud(client, href, path);

        return result;
    }

    @Override
    public JsonObject uploadExternalFile(File path, URL url) {
        HttpUrl uploadPath = ULOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getPath())
                .addQueryParameter("url", url.toString())
                .build();

        request = postRequest(uploadPath, Util.EMPTY_REQUEST, tempAccessToken);
        result = sendRequest(client, request);

        return result;
    }

    @Override
    public JsonObject downloadFile(File path) {
        HttpUrl downloadPath = DLOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getPath())
                .build();

        request = getRequest(downloadPath, tempAccessToken);
        result = sendRequest(client, request);
        String href = result.get("href").toString();
        href = href.substring(1, href.length() - 1);
        getFileFromCloud(client, href, path);

        return result;
    }

    @Override
    public JsonObject copyFile(File from, File to) {
        HttpUrl copyPath = COPY_PATH.newBuilder()
                .addQueryParameter("from", APP_ROOT + from.getPath())
                .addQueryParameter("path", APP_ROOT + to.getPath())
                .build();

        request = postRequest(copyPath, Util.EMPTY_REQUEST, tempAccessToken);
        result = sendRequest(client, request);

        return result;
    }

    @Override
    public JsonObject moveFile(File from, File to) {
        HttpUrl movePath = MOVE_PATH.newBuilder()
                .addQueryParameter("from", APP_ROOT + from.getPath())
                .addQueryParameter("path", APP_ROOT + to.getPath())
                .build();

        request = postRequest(movePath, Util.EMPTY_REQUEST, tempAccessToken);
        result = sendRequest(client, request);

        return result;
    }

    @Override
    public JsonObject createDirectory(File path) {
        HttpUrl dirPath;

        if (path.getParent() == null) {
            dirPath = RESOURCES_PATH.newBuilder()
                    .addQueryParameter("path", APP_ROOT + path.getName())
                    .build();
        } else {
            createDirectory(path.getParentFile());
            dirPath = RESOURCES_PATH.newBuilder()
                    .addQueryParameter("path",
                            APP_ROOT + slashSwap(path.getParent() + '/' + path.getName()))
                    .build();
        }

        request = putRequest(dirPath, Util.EMPTY_REQUEST, tempAccessToken);
        result = sendRequest(client, request);
        return result;
    }

    @Override
    public JsonObject deleteResource(File path) {
        HttpUrl deletePath = RESOURCES_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getPath())
                .build();

        request = deleteRequest(deletePath, tempAccessToken);
        result = sendRequest(client, request);

        return result;
    }

    @Override
    public JsonObject getResourceMetadata(File path) {
        HttpUrl resourcePath = RESOURCES_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getPath())
                .build();

        request = getRequest(resourcePath, tempAccessToken);
        result = sendRequest(client, request);

        return result;
    }

    @Override
    public JsonObject getCloudStorageMetadata() {
        request = getRequest(DISK_PATH, tempAccessToken);
        result = sendRequest(client, request);
        return result;
    }

    @Override
    public JsonObject getCloudStorageAuthToken() {
        if (verificationCode == null) {
            //FIXME Somehow we need to set the verification code value that a user will see at a page
            //How to call setVerificationCode(..) _conveniently_? Maybe popup-window?
            openOAuthPage();
        } else {
            String authCred = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
            String bodyParam = "grant_type=authorization_code&code=" + verificationCode;

            Request request = new Request.Builder()
                    .url(TOKEN_REQ_PATH)
                    .header("Authorization", authCred)
                    .post(RequestBody.create(MediaTypes.JSON.getType(), bodyParam))
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.code() == 200) {
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response.body().string()).getAsJsonObject();
                    accessToken = AccessToken.buildAccessToken(o);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public JsonObject revokeCloudStorageAuthToken() {
        //TODO think how to do it better
        return null;
    }

    private String slashSwap(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\') {
                sb.append('/');
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
