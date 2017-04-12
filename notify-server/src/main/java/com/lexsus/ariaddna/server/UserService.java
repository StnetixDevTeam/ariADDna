package com.lexsus.ariaddna.server;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * Created by LugovoyAV on 04.04.2017.
 */
public interface UserService {
    void sendMessage(ClientInfo clientInfo, String text) throws IOException;
    void sendMessageAll(String text) throws IOException;
    void addClient(Session session);
    void removeClient(Session session);
}
