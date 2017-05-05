package com.stnetix.ariaddna.pushnotification.client;

import com.stnetix.ariaddna.pushnotification.server.IPushConsume;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ClientMessageSystem<E> {
    IPushConsume<E> consumer;
    @Autowired
    ClientSocketService clientService;

    public ClientMessageSystem(IPushConsume<E> consumer) {
        this.consumer = consumer;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    private boolean   isStopped;
    public void runMessagesSystem() {
        isStopped = false;
        try {
            clientService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (!isStopped) {
                consumer.consume();

            }
        }).start();
    }

}
