package com.stnetix.ariaddna.pushnotification.server;

/**
 * Created by LugovoyAV on 03.04.2017.
 */
public interface MessageProcessor<E> {
    void process(E message);
}
