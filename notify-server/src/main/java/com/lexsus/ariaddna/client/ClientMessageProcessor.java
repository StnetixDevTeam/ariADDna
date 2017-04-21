package com.lexsus.ariaddna.client;

import com.lexsus.ariaddna.server.MessageProcessor;
import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class ClientMessageProcessor<E> implements MessageProcessor<E>{
    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(ClientMessageProcessor.class);
    @Override
    public void process(E message) {

        LOGGER.debug("[ClientMessageProcessor]: get message:"+message);
    }
}
