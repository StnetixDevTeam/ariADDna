package com.lexsus.ariaddna.server;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ServerMessageSystem {
    IPushConsumeServer<String> consumer;
    IPushProduceServer<String> producer;
    boolean isStopped = false;
    public ServerMessageSystem(IPushProduceServer<String> producer,IPushConsumeServer<String> consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    public void runMessagesSystem() {
        isStopped = false;
        new Thread(() -> {
            while (!isStopped) {

               consumer.consume();

            }
        }).start();
        new Thread(() -> {
            while (!isStopped) {
                producer.produce();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }
}
