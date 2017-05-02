package com.stnetix.ariaddna.pushnotification.server;

import com.google.gson.JsonObject;

/**
 * Created by LugovoyAV on 20.04.2017.
 */
public class JSONServerMessageGenerator implements MessageGenerator<JsonObject>{
    private int count;
    @Override
    public JsonObject generate() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Text", "TestMessage");
        jsonObject.addProperty("Text", count++);
        return  jsonObject;
    }
}

