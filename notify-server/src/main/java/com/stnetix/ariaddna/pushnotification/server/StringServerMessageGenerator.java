package com.stnetix.ariaddna.pushnotification.server;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class StringServerMessageGenerator implements MessageGenerator<String>{
    private int count;
    @Override
    public String generate() {
        return "Test message "+count++;
    }
}
