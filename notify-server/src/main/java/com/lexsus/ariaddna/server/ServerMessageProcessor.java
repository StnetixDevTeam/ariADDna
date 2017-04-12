package com.lexsus.ariaddna.server;

import org.eclipse.jetty.websocket.api.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class ServerMessageProcessor implements  MessageProcessor<String>
{
//    @Autowired
//    private SharedMap<Session,ClientInfo> sharedMap;
    @Autowired
    private UserService service;

    @Override
    public void process(String message) {
        try {
            service.sendMessageAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
