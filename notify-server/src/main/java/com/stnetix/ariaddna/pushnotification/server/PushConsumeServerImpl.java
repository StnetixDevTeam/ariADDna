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
            if (element == null) {
                System.out.println("null message");
                return;//TODO // FIXME: 13.04.2017 first element == null
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageProcessor.process(element);
    }
}

