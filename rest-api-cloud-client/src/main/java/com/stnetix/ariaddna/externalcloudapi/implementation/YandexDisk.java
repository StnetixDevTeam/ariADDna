package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stnetix.ariaddna.externalcloudapi.AccessToken;
import com.stnetix.ariaddna.externalcloudapi.MediaTypes;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.IAbstractCloud;
import static com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper.*;
import okhttp3.*;
import okhttp3.internal.Util;

import java.awt.*;
import java.io.*;
import java.net.URL;


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

    private void openOAuthPage(){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(VCODE_REQ_PATH.uri());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVerificationCode(String value){
        this.verificationCode = value;
    }

    public String getVerificationCode(){
        return this.verificationCode;
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
        href = href.substring(1, href.length() -1 );
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

        if(path.getParent() == null){
            dirPath = RESOURCES_PATH.newBuilder()
                    .addQueryParameter("path", APP_ROOT + path.getName())
                    .build();
        } else {
            createDirectory(path.getParentFile());
            dirPath = RESOURCES_PATH.newBuilder()
                    .addQueryParameter("path", APP_ROOT + slashSwap(path.getParent() + '/' + path.getName()))
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

        return  result;
    }

    @Override
    public JsonObject getCloudStorageMetadata() {
        request = getRequest(DISK_PATH, tempAccessToken);
        result = sendRequest(client, request);
        return result;
    }

    @Override
    public JsonObject getCloudStorageAuthToken() {
        if(verificationCode == null) {
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
                if(response.code() == 200) {
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
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '\\') {
                sb.append('/');
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
