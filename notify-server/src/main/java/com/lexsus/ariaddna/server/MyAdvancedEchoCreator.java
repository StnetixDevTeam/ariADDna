package com.lexsus.ariaddna.server;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Lexsus on 01.04.2017.
 */
public class MyAdvancedEchoCreator implements WebSocketCreator
{
    private ToUpperWebSocket textEcho;
    public MyAdvancedEchoCreator(UserService service)
    {
        this.textEcho = new ToUpperWebSocket(service);
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
    {
        return textEcho;
    }
}
