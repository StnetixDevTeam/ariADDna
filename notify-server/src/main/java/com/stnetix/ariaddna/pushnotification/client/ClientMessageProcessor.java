package com.stnetix.ariaddna.pushnotification.client;

import com.stnetix.ariaddna.pushnotification.server.MessageProcessor;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class ClientMessageProcessor<E> implements MessageProcessor<E>{
    @Override
    public void process(E message) {

        System.out.println("[ClientMessageProcessor]: get message:"+message);
    }
}
