package com.stnetix.ariaddna.externalcloudapi;

import okhttp3.HttpUrl;

public enum UrlStorage {
    HTTPS_SCHEME("https"),
    YD_HOST("cloud-api.yandex.net"),
    DB_HOST("dropbox.com");

    private String url;

    UrlStorage(String url){
        this.url = url;
    }

    public static HttpUrl getDiskUrl(UrlStorage host){
        return new HttpUrl.Builder()
                .scheme(HTTPS_SCHEME.getUrl())
                .host(host.getUrl())
                .build();
    }

    public String getUrl(){
        return url;
    }

    public static void main(String[] args) {
        System.out.println(getDiskUrl(YD_HOST));
    }
}
