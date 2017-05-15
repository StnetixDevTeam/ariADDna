package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class YandexDisk implements iAbstractCloud {

    private static final String HOST_URL = "cloud-api.yandex.net";
    OkHttpClient client = new OkHttpClient();

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
        return null;
    }

    @Override
    public JsonObject revokeCloudStorageAuthToken() {
        Request request = new Request.Builder()
                .url(HOST_URL)
                .build();
        try {
            Response response = client.newCall(request).execute();
            response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        YandexDisk yandexDisk = new YandexDisk();
        yandexDisk.getCloudStorageAuthToken();
    }
}
