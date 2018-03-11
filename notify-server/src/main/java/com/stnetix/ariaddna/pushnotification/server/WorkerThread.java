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

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.eclipse.jetty.websocket.api.Session;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

/**
 * Created by LugovoyAV on 06.04.2017.
 */
public class WorkerThread<E> implements Runnable {
    private static final AriaddnaLogger LOGGER =
            AriaddnaLogger.getLogger(PushProduceServerImpl.class);

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
                    if (session.isOpen()) {
                        session.getRemote().sendString(String.valueOf(message));
                        LOGGER.debug("send message:" + message);
                    } else {
                        LOGGER.debug("Can't send message session is closed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
