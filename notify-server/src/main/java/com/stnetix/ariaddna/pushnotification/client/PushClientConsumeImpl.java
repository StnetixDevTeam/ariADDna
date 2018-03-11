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

package com.stnetix.ariaddna.pushnotification.client;

import com.stnetix.ariaddna.pushnotification.server.IPushConsume;
import com.stnetix.ariaddna.pushnotification.server.MessageProcessor;
import com.stnetix.ariaddna.pushnotification.server.SharedQueue;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class PushClientConsumeImpl<E> implements IPushConsume<E> {
    private SharedQueue<E> sharedQueue;
    private MessageProcessor<E> processor;

    public PushClientConsumeImpl(SharedQueue<E> sharedQueue, MessageProcessor<E> processor) {
        this.sharedQueue = sharedQueue;
        this.processor = processor;
    }

    @Override
    public void consume() {
        try {
            E message = sharedQueue.take();
            processor.process(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
