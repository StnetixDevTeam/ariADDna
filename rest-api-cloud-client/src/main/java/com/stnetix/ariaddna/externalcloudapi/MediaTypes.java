package com.stnetix.ariaddna.externalcloudapi;

import okhttp3.MediaType;

/**
 * From this enum one can pull out necessary Media Types and give it to OkHttp Request Builder.
 */
public enum MediaTypes {
    JSON("application/json; charset=utf-8"),
    JPG("image/jpeg");

    private MediaType type;

    MediaTypes(String type){
        this.type = MediaType.parse(type);
    }

    public MediaType getType(){
        return type;
    }
}
