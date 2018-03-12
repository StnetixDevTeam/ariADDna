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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.stnetix.ariaddna.pushnotification.server.SharedQueue;

/**
 * Created by Lexsus on 30.03.2017.
 */
public class ClientSocketService<E> implements ClientSocketListener<E> {

    public static final int DURATION = 5;
    private String dest;
    WebSocketClient client = new WebSocketClient();
    private SharedQueue<E> queue;

    public ClientSocketService(String dest, SharedQueue<E> queue) {
        this.dest = dest;
        this.queue = queue;
    }

    @Override
    public void onMessage(E message) {
        try {
            queue.put(message);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {

        AutoConnectSocket socket = new AutoConnectSocket(this);
        try {
            client.start();
            connect(socket);
        } catch (Throwable t) {
            t.printStackTrace();
            client.stop();
        }
    }

    private void connect(AutoConnectSocket socket) {
        URI echoUri = null;
        try {
            echoUri = new URI(dest);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        try {
            client.connect(socket, echoUri, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Connecting to : %s%n", echoUri);

        // wait for closed socket connection.
        try {
            socket.awaitClose(DURATION, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }

    @Override
    public void onClose(int statusCode, E reason) {
        if (client.isRunning()) {
            connect(new AutoConnectSocket(this));
        }
    }
}
