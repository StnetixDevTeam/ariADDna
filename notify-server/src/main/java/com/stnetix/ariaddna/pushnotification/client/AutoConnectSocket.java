package com.stnetix.ariaddna.pushnotification.client;


import java.util.concurrent.*;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket(maxTextMessageSize = 64 * 1024)
public class AutoConnectSocket
{
    private final CountDownLatch closeLatch;
    private ClientSocketListener listener;
    private Session session;

    public AutoConnectSocket(ClientSocketListener listener)
    {
        this.closeLatch = new CountDownLatch(1);
        this.listener = listener;
    }


    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration,unit);
    }


    public  void awaitClose() throws InterruptedException
    {
        this.closeLatch.await();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
        this.session = null;
        this.closeLatch.countDown(); // trigger latch
        listener.onClose(statusCode,reason);
    }

    @OnWebSocketConnect
    public void onConnect(Session session)
    {
        System.out.printf("Got connect: %s%n",session);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String msg) throws InterruptedException {
        listener.onMessage(msg);

    }
}
