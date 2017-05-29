package com.stnetix.ariaddna.externalcloudapi;

import okhttp3.HttpUrl;

import java.net.URL;

import okhttp3.HttpUrl;

/**
 * HttpConstants class contains different text fragments which are to help a programmer
 * construct any desired URL for request to an external cloud.
 *
 * The class is final to prevent it's inheritance.
 * Also it contains private constructor to forbid instantiating of this class.
 */

//TODO Переделать в UrlFactory
public final class HttpConstants {
    public enum Clouds{
        YandexDisk,
        DropBox
    }

    public static final String HTTPS_SCHEME  = "https";

    public static final String YD_HOST = "cloud-api.yandex.net";
    public static final String YD_OAUTH_HOST = "oauth.yandex.ru";
    public static final String YD_API_VER = "v1";
    public static final String YD_ROOT = "disk";
    public static final String YD_RESOURSES = "resources";

    public static final String DB_HOST = "dropbox.com";


    public static HttpUrl getOAuthUrl(Clouds cloud){
        String host;

        switch (cloud){
            case DropBox:
                host = DB_HOST;
                break;
            case YandexDisk:
                host = YD_HOST;
                break;

            default:
                host = "";
        }

        return new HttpUrl.Builder()
                .scheme(HTTPS_SCHEME)
                .host(host)
                .build();
    }

    public static void main(String[] args) {
        System.out.println(getOAuthUrl(Clouds.DropBox));
    }
    public static HttpUrl get() {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("fff")
                .build();
        System.out.println(httpUrl.toString());
        httpUrl = httpUrl.newBuilder().addPathSegment("something").build();
        System.out.println(httpUrl.toString());
        return null;
    }

    public static HttpUrl getOAuthUrl(){
        return null;
    }

    private HttpConstants(){

    }
}
