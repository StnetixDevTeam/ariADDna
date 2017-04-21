package com.lexsus.ariaddna.server;

import jdk.nashorn.internal.parser.JSONParser;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * Created by LugovoyAV on 20.04.2017.
 */
public class JSONServerMessageGenerator implements MessageGenerator<JsonObject>{
    private int count;
    @Override
    public JsonObject generate() {
        return Json.createObjectBuilder()
                .add("Text", "TestMessage")
                .add("index", count++)
                .build();

    }
}

