package com.lexsus.ariaddna.server;

import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class PushConsumeServerImpl<E> implements IPushConsumeServer<E> {

    private SharedQueue<E> sharedQueue;
    //@Autowired
    //TODO If you need send individual messages to client.
    private SharedQueue<ClientInfo> sharedClients;

    protected MessageProcessor messageProcessor;

    public PushConsumeServerImpl(SharedQueue sharedQueue, MessageProcessor messageProcessor) {
        this.sharedQueue = sharedQueue;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void consume() {
        E element = null;
        try {
            element = sharedQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageProcessor.process(element);
    }
}

