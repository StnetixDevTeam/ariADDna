package com.lexsus.ariaddna.server;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by LugovoyAV on 06.04.2017.
 */
public class WorkerThread<E> implements Runnable{
    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    private boolean isStopped = false;

    private BlockingQueue<E> queue;
    private Session session;

    public WorkerThread(BlockingQueue<E> queue, Session session) {
        this.queue = queue;
        this.session = session;
    }


    @Override
    public void run() {
        processCommand();
    }

    private void processCommand() {
        while (!isStopped) {
            try {
                E message = queue.take();
                try {
                    //TODO Future<Void>
                    //TODO check String.valueOf(message)
                    session.getRemote().sendString(String.valueOf(message));
                    System.out.println("send message:"+message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
