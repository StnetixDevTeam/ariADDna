package com.stnetix.ariaddna.pushnotification.server;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Lexsus on 01.04.2017.
 */
public class AriaddnaWebSocketCreator implements WebSocketCreator
{
    private AriaddnaWebSocket ariaddnaWebSocket;
    public AriaddnaWebSocketCreator(UserService service)
    {
        this.ariaddnaWebSocket = new AriaddnaWebSocket(service);
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        return ariaddnaWebSocket;
    }

}
