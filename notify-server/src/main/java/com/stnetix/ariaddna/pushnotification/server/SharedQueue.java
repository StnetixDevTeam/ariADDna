/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.pushnotification.server;

import java.util.concurrent.BlockingQueue;
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
