package com.lexsus.ariaddna.client;

import com.lexsus.ariaddna.server.IPushConsume;
import com.lexsus.ariaddna.server.IPushProduce;
import com.lexsus.ariaddna.server.MessageProcessor;
import com.lexsus.ariaddna.server.SharedQueue;

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
