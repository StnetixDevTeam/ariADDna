package com.stnetix.ariaddna.pushnotification.server;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

@WebSocket
public class AriaddnaWebSocket {

    private UserService service;
    private SharedQueue<Session> queue;
    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(AriaddnaWebSocket.class);
    public AriaddnaWebSocket(UserService service) {
        this.service = service;
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {

        if (session.isOpen()) {

            String response = message.toUpperCase();

            session.getRemote().sendString(response);

        }

    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        LOGGER.debug(session.getRemoteAddress().getHostString() + " connected!");

        service.addClient(session);

    }


    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        LOGGER.debug(session.getRemoteAddress().getHostString() + " closed!");
        service.removeClient(session);
    }

}
