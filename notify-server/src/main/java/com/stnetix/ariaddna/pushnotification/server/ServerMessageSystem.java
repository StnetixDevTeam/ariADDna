package com.stnetix.ariaddna.pushnotification.server;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ServerMessageSystem <E>{
    IPushConsume<E> consumer;
    IPushProduce<E> producer;
    boolean isStopped = false;
    public ServerMessageSystem(IPushProduce<E> producer, IPushConsume<E> consumer) {
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
