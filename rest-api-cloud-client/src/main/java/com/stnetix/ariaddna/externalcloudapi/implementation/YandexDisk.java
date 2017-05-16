package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class YandexDisk implements iAbstractCloud {

    private static final String HOST_URL = "https://cloud-api.yandex.net";

    private static final String OAUTH_HOST = "oauth.yandex.ru";

    private static final String CLIENT_ID = "e8c6429fe5a3432cb13db5c000200b54";

    private String verificationCode;

    private OkHttpClient client;

    public YandexDisk() {
        client = new OkHttpClient();
    }

    @Override
    public JsonObject uploadFile(File path) {
        return null;
    }

    @Override
    public JsonObject uploadExternalFile(File path, URL url) {
        return null;
    }

    @Override
    public JsonObject downloadFile(File path) {
        return null;
    }

    @Override
    public JsonObject copyFile(File from, File to) {
        return null;
    }

    @Override
    public JsonObject moveFile(File from, File to) {
        return null;
    }

    @Override
    public JsonObject createDirectory(File path) {
        return null;
    }

    @Override
    public JsonObject deleteResource(File path) {
        return null;
    }

    @Override
    public JsonObject getResourceMetadata(File path) {
        return null;
    }

    @Override
    public JsonObject getCloudStorageMetadata() {
        return null;
    }

    @Override
    public JsonObject getCloudStorageAuthToken() {
/*        Request request = new Request.Builder()
                .url(HOST_URL)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        HttpUrl authUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(OAUTH_HOST)
                .addPathSegment("authorize")
                .addQueryParameter("response_type", "code")
                .addQueryParameter("client_id", CLIENT_ID)
                .build();


        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(authUrl.uri());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Request request = new Request.Builder()
                .url(authUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonObject revokeCloudStorageAuthToken() {
        return null;
    }

    public static void main(String[] args) {
        YandexDisk yandexDisk = new YandexDisk();
        yandexDisk.getCloudStorageAuthToken();
    }
}
