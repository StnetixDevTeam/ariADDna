package com.lexsus.ariaddna.server;

import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

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
