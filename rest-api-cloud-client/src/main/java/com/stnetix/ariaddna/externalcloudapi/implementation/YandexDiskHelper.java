package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stnetix.ariaddna.externalcloudapi.MediaTypes;
import okhttp3.*;

import java.io.*;

public class YandexDiskHelper {

    private static JsonParser parser;

    static final String APP_ROOT = "app:/";

    static Request baseRequest;

    static final HttpUrl DISK_PATH;

    static final HttpUrl RESOURCES_PATH;

    static final HttpUrl COPY_PATH;

    static final HttpUrl MOVE_PATH;

    static final HttpUrl DLOAD_PATH;

    static final HttpUrl ULOAD_PATH;

    static final HttpUrl FILES_PATH;

    static final HttpUrl VCODE_REQ_PATH;

    static final HttpUrl TOKEN_REQ_PATH;

    static final String CLIENT_ID = "141588389ce24e4e80e6ccd5db81c7a6";

    static final String CLIENT_SECRET = "7fbc2f7214b64d53809249b5534291d2";

    static {
        parser = new JsonParser();

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

        baseRequest = new Request.Builder()
                .url(DISK_PATH)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .get()
                .build();
    }

    static Request getRequest(HttpUrl url, String token){
        return baseRequest.newBuilder()
                .header("Authorization", "OAuth " + token)
                .get()
                .tag(null)
                .build();
    }

    static Request postRequest(HttpUrl url, RequestBody body, String token){
        return baseRequest.newBuilder()
                .header("Authorization", "OAuth " + token)
                .post(body)
                .tag(null)
                .build();
    }

    static Request putRequest(HttpUrl url, RequestBody body, String token){
        return baseRequest.newBuilder()
                .header("Authorization", "OAuth " + token)
                .put(body)
                .tag(null)
                .build();
    }

    static Request deleteRequest(HttpUrl url, String token){
        return baseRequest.newBuilder()
                .header("Authorization", "OAuth " + token)
                .delete()
                .tag(null)
                .build();
    }

    static JsonObject sendRequest(OkHttpClient client, Request request){
        JsonObject result = new JsonObject();

        try {
            Response response = client.newCall(request).execute();
            result = parser.parse(response.body().string()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static void getFileFromCloud(OkHttpClient client, String href, File path){
        Request request = new Request.Builder()
                .url(href)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
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

    static void sendFileToCloud(OkHttpClient client, String href, File path){
        Response response;

        try {
            Request request = new Request.Builder()
                    .url(href)
                    .put(RequestBody.create(MediaTypes.JPG.getType(), path))
                    .build();
            response = client.newCall(request).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
