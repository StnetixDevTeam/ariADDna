package com.stnetix.ariaddna.pushnotification.server;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class ServerMessageProcessor<E> implements  MessageProcessor<E>
{
//    @Autowired
//    private SharedMap<Session,ClientInfo> sharedMap;
    @Autowired
    private UserService service;

    @Override
    public void process(E message) {
        try {
            service.sendMessageAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
