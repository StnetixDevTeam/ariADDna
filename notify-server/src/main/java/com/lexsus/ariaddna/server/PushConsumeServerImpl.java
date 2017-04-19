package com.lexsus.ariaddna.server;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class PushConsumeServerImpl<E> implements IPushConsume<E> {

    private SharedQueue<E> sharedQueue;
    //TODO If you need send individual messages to client.


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
            if (element==null)
            {
                System.out.println("null message");
                return;//TODO // FIXME: 13.04.2017 first element == null
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageProcessor.process(element);
    }
}

