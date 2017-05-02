package com.stnetix.ariaddna.pushnotification.server;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * Created by LugovoyAV on 04.04.2017.
 */
public interface UserService<E> {
    void sendMessage(ClientInfo clientInfo, E message) throws IOException;
    void sendMessageAll(E message) throws IOException;
    void addClient(Session session);
    void removeClient(Session session);
}
