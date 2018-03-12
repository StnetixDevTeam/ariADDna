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

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ServerMessageSystem<E> {
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
