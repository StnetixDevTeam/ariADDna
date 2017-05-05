package com.stnetix.ariaddna.pushnotification.client;

import com.stnetix.ariaddna.pushnotification.server.IPushConsume;
import com.stnetix.ariaddna.pushnotification.server.MessageProcessor;
import com.stnetix.ariaddna.pushnotification.server.SharedQueue;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class PushClientConsumeImpl<E> implements IPushConsume<E> {
    private SharedQueue<E> sharedQueue;

    public PushClientConsumeImpl(SharedQueue<E> sharedQueue, MessageProcessor<E> processor) {
        this.sharedQueue = sharedQueue;
        this.processor = processor;
    }

    private MessageProcessor<E> processor;
    @Override
    public void consume() {
        try {
            E message = sharedQueue.take();
            processor.process(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
