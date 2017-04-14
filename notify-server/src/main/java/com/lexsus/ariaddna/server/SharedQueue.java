package com.lexsus.ariaddna.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class SharedQueue<E> {
    //private final ConcurrentLinkedQueue<E> queue;
    private final BlockingQueue<E> queue;
    public SharedQueue() {

        //this.queue = new ConcurrentLinkedQueue<>();
        this.queue = new LinkedBlockingQueue<E>();
    }

    public E take() throws InterruptedException {
        return queue.take();
    }

    public void put(E e) throws InterruptedException {
        queue.put(e);
    }
}
