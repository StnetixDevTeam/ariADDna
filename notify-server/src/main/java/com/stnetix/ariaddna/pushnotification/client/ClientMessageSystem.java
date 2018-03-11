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

import org.springframework.beans.factory.annotation.Autowired;

import com.stnetix.ariaddna.pushnotification.server.IPushConsume;

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

    private boolean isStopped;

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
