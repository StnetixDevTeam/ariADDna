package com.stnetix.ariaddna.externalcloudapi;

/**
 * HttpConstants class contains different text fragments which are to help a programmer
 * construct any desired URL for request to an external cloud.
 *
 * The class is final to prevent it's inheritance.
 * Also it contains private constructor to forbid instantiating of this class.
 */
public final class HttpConstants {
    public static final String HTTPS_SCHEME  = "https";

    public static final String YANDEX_DISK_HOST = "cloud-api.yandex.net";
    public static final String YANDEX_DISK_OAUTH_HOST = "oauth.yandex.ru";
    public static final String YANDEX_DISK_API_VER = "v1";
    public static final String YANDEX_DISK_ROOT = "disk";

    private HttpConstants(){

    }
}
