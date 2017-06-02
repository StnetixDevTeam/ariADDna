package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stnetix.ariaddna.externalcloudapi.AccessToken;
import com.stnetix.ariaddna.externalcloudapi.Clouds;
import com.stnetix.ariaddna.externalcloudapi.MediaTypes;
import com.stnetix.ariaddna.externalcloudapi.UrlFactory;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.IAbstractCloud;
import okhttp3.*;
import okhttp3.internal.Util;

import java.awt.*;
import java.io.*;
import java.net.URL;

import static com.stnetix.ariaddna.externalcloudapi.UrlFactory.getOAuthUrl;

//TODO Выделить в отдельный модуль все хосты и огрызки URL'ов
//TODO Отправлять в БД настройки облака
//TODO Продумать способ хранения множества аккаунтов

public class YandexDisk implements IAbstractCloud {

    private JsonParser parser;

    private AccessToken accessToken;

    private static final HttpUrl DISK_PATH;

    private static final HttpUrl RESOURCES_PATH;

    private static final HttpUrl COPY_PATH;

    private static final HttpUrl MOVE_PATH;

    private static final HttpUrl DLOAD_PATH;

    private static final HttpUrl ULOAD_PATH;

    private static final HttpUrl FILES_PATH;

    private static final HttpUrl VCODE_REQ_PATH;

    private static final HttpUrl TOKEN_REQ_PATH;

    private static final String CLIENT_ID = "141588389ce24e4e80e6ccd5db81c7a6";

    private static final String CLIENT_SECRET = "7fbc2f7214b64d53809249b5534291d2";

    private String verificationCode = "3650961";

    private String tempAccessToken = "AQAAAAAeFNwNAARF-Q0jtdjwhUH6mR6qL3eeGhg";

    private static String APP_ROOT = "app:/";

    private OkHttpClient client;

    static {
        DISK_PATH = new HttpUrl.Builder()
                .scheme("https")
                .host("cloud-api.yandex.net")
                .addPathSegment("v1")
                .addPathSegment("disk")
                .build();

        VCODE_REQ_PATH = new HttpUrl.Builder()
                .scheme("https")
                .host("oauth.yandex.ru")
                .addPathSegment("authorize")
                .addQueryParameter("response_type", "code")
                .addQueryParameter("client_id", CLIENT_ID)
                .build();

        TOKEN_REQ_PATH = new HttpUrl.Builder()
                .scheme("https")
                .host("oauth.yandex.ru")
                .addPathSegment("token")
                .build();

        RESOURCES_PATH = DISK_PATH.newBuilder()
                .addPathSegment("resources")
                .build();

        COPY_PATH = RESOURCES_PATH.newBuilder()
                .addPathSegment("copy")
                .build();

        MOVE_PATH = RESOURCES_PATH.newBuilder()
                .addPathSegment("move")
                .build();

        ULOAD_PATH = RESOURCES_PATH.newBuilder()
                .addPathSegment("upload")
                .build();

        DLOAD_PATH = RESOURCES_PATH.newBuilder()
                .addPathSegment("download")
                .build();

        FILES_PATH = RESOURCES_PATH.newBuilder()
                .addPathSegment("files")
                .build();
    }

    public YandexDisk() {
        client = new OkHttpClient();
        parser = new JsonParser();
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

    private JsonObject sendRequest(Request request){
        JsonObject result = new JsonObject();

        try {
            Response response = client.newCall(request).execute();
            result = parser.parse(response.body().string()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void getFileFromCloud(String href, File path){
        Request request = new Request.Builder()
                .url(href)
                .get()
                .build();

        Response response;


        try {
            response = client.newCall(request).execute();
            if(response.code() == 200) {
                if (path.exists()) {
                    path.delete();
                }

                path.createNewFile();

                InputStream is = response.body().byteStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(path);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int cur;
                while ((cur = bis.read()) != -1) {
                    bos.write(cur);
                }
                bos.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFileToCloud(String href, File path){
        Response response;

        //Use path instead
        File file = new File("Мишки.jpg");

        try {
            Request request = new Request.Builder()
                    .url(href)
                    .put(RequestBody.create(MediaTypes.JPG.getType(), file))
                    .build();
            response = client.newCall(request).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonObject uploadFile(File path) {
        Request request;
        JsonObject result;

        HttpUrl uploadPath = ULOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getName()).build();

        request = new Request.Builder()
                .url(uploadPath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .get()
                .build();

        result = sendRequest(request);
        String href = result.get("href").toString();
        href = href.substring(1, href.length() -1 );
        sendFileToCloud(href, path);

        return result;
    }

    @Override
    public JsonObject uploadExternalFile(File path, URL url) {
        JsonObject result;

        HttpUrl uploadPath = ULOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getName())
                .addQueryParameter("url", url.toString())
                .build();

        Request request = new Request.Builder()
                .url(uploadPath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .post(Util.EMPTY_REQUEST)
                .build();

        result = sendRequest(request);

        return result;
    }

    @Override
    public JsonObject downloadFile(File path) {
        Request request;
        JsonObject result;

        HttpUrl downloadPath = DLOAD_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getName())
                .build();

            request = new Request.Builder()
                    .url(downloadPath)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "OAuth " + tempAccessToken)
                    .get()
                    .build();
        result = sendRequest(request);
        String href = result.get("href").toString();
        href = href.substring(1, href.length() -1 );
        getFileFromCloud(href, path);

        return result;
    }

    @Override
    public JsonObject copyFile(File from, File to) {
        JsonObject result;

        HttpUrl copyPath = COPY_PATH.newBuilder()
                .addQueryParameter("from", APP_ROOT + from.getName())
                .addQueryParameter("path", APP_ROOT + to.getPath())
                .build();

        Request request = new Request.Builder()
                .url(copyPath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .post(Util.EMPTY_REQUEST)
                .build();

        result = sendRequest(request);

        return result;
    }

    @Override
    public JsonObject moveFile(File from, File to) {
        JsonObject result;

        HttpUrl movePath = MOVE_PATH.newBuilder()
                .addQueryParameter("from", APP_ROOT + from.getName())
                .addQueryParameter("path", APP_ROOT + to.getPath())
                .build();

        Request request = new Request.Builder()
                .url(movePath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .post(Util.EMPTY_REQUEST)
                .build();

        result = sendRequest(request);

        return result;
    }

    @Override
    public JsonObject createDirectory(File path) {
        JsonObject result;
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

        Request request = new Request.Builder()
                .url(dirPath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .put(Util.EMPTY_REQUEST)
                .build();
        result = sendRequest(request);
        return result;
    }

    @Override
    public JsonObject deleteResource(File path) {
        JsonObject result;

        HttpUrl deletePath = RESOURCES_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getName())
                .build();

        Request request = new Request.Builder()
                .url(deletePath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .delete()
                .build();
        result = sendRequest(request);

        return result;
    }

    @Override
    public JsonObject getResourceMetadata(File path) {
        JsonObject result;

        HttpUrl resourcePath = RESOURCES_PATH.newBuilder()
                .addQueryParameter("path", APP_ROOT + path.getName())
                .build();

        Request request = new Request.Builder()
                .url(resourcePath)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .get()
                .build();

        result = sendRequest(request);

        return  result;
    }

    @Override
    public JsonObject getCloudStorageMetadata() {
        JsonObject result;
        Request request = new Request.Builder()
                .url(DISK_PATH)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .get()
                .build();

        result = sendRequest(request);
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
                    .post(RequestBody.create(MediaTypes.JSON.getType(), bodyParam ))
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
