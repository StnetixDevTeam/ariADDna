package com.stnetix.ariaddna.externalcloudapi.implementation;

import static com.stnetix.ariaddna.externalcloudapi.HttpConstants.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stnetix.ariaddna.externalcloudapi.AccessToken;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import okhttp3.*;
import okhttp3.internal.Util;

import java.awt.*;
import java.io.*;
import java.net.URL;

//TODO Выделить в отдельный модуль все хосты и огрызки URL'ов
//TODO Отправлять в БД настройки облака
//TODO Продумать способ хранения множества аккаунтов

public class YandexDisk implements iAbstractCloud {

    private JsonParser parser;

    private AccessToken accessToken;

    private static final String HOST_URL = "cloud-api.yandex.net";

    private static final String OAUTH_HOST = "oauth.yandex.ru";

    private static final String CLIENT_ID = "141588389ce24e4e80e6ccd5db81c7a6";

    private static final String CLIENT_SECRET = "7fbc2f7214b64d53809249b5534291d2";

    private String verificationCode = "3650961";

    private String tempAccessToken = "AQAAAAAeFNwNAARF-Q0jtdjwhUH6mR6qL3eeGhg";

    private String tempAccessToken2 = "AQAAAAAeFNwNAARF-Tixdh81n007h52kXupp1qg";

    private static String DISK_ROOT = "disk:/";

    private static String APP_ROOT = "app:/";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType JPG
            = MediaType.parse("image/jpeg");

    private OkHttpClient client;

    public YandexDisk() {
        client = new OkHttpClient();
        parser = new JsonParser();
    }

    private HttpUrl constructOAuthUrl(){
        return getOAuthUrl(Clouds.DropBox);
       /* return new HttpUrl.Builder()
                .scheme("https")
                .host(OAUTH_HOST)
                .addPathSegment("authorize")
                .addQueryParameter("response_type", "code")
                .addQueryParameter("client_id", CLIENT_ID)
                .build();*/
    }

    private void openOAuthPage(){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(constructOAuthUrl().uri());
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
          //  System.out.println(response.body().string());
            if(response.code() == 200) {
                result = parser.parse(response.body().string()).getAsJsonObject();
            }
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
                int cur = 0;
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
                    .put(RequestBody.create(JPG, file))
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
        request = new Request.Builder()
                .url("https://" + HOST_URL + "/v1/disk/resources/upload?path=" +
                        APP_ROOT + path.getName())
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
        return null;
    }

    @Override
    public JsonObject downloadFile(File path) {
        Request request;
        JsonObject result;
            request = new Request.Builder()
                    .url("https://" + HOST_URL + "/v1/disk/resources/download?path=" +
                            APP_ROOT + path.getName())
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
        return null;
    }

    @Override
    public JsonObject moveFile(File from, File to) {
        return null;
    }

    @Override
    public JsonObject createDirectory(File path) {
        JsonObject result;

        if(path.getParent() == null){
            Request request = new Request.Builder()
                    .url("https://" + HOST_URL + "/v1/disk/resources?path=" +
                            APP_ROOT + path.getName())
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "OAuth " + tempAccessToken)
                    .put(Util.EMPTY_REQUEST)
                    .build();
            result = sendRequest(request);
            return result;
        } else {
            createDirectory(path.getParentFile());
            Request request = new Request.Builder()
                    .url("https://" + HOST_URL + "/v1/disk/resources?path=" +
                            APP_ROOT + slashSwap(path.getParent() + '/' + path.getName()))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "OAuth " + tempAccessToken)
                    .put(Util.EMPTY_REQUEST)
                    .build();
            result = sendRequest(request);
            return result;
        }
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
        JsonObject result;
        Request request = new Request.Builder()
                .url("https://" + HOST_URL + "/v1/disk")
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
                    .url("https://" + OAUTH_HOST + "/token")
                    .header("Authorization", authCred)
                    .post(RequestBody.create(JSON, bodyParam ))
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
