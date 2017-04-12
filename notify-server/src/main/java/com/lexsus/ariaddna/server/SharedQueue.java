package com.lexsus.ariaddna.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public class SharedQueue<E> {
    private final ConcurrentLinkedQueue<E> queue;

    public SharedQueue() {

        this.queue = new ConcurrentLinkedQueue<>();
    }

    public E take() throws InterruptedException {
        return queue.poll();
    }

    public void put(E e) throws InterruptedException {
        queue.add(e);
    }
}
