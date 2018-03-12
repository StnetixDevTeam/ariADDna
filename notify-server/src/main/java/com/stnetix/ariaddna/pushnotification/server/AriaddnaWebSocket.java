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

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

@WebSocket
public class AriaddnaWebSocket {

    private UserService service;
    private SharedQueue<Session> queue;
    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(AriaddnaWebSocket.class);

    public AriaddnaWebSocket(UserService service) {
        this.service = service;
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {

        if (session.isOpen()) {

            String response = message.toUpperCase();

            session.getRemote().sendString(response);

        }

    }

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        LOGGER.debug(session.getRemoteAddress().getHostString() + " connected!");

        service.addClient(session);

    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        LOGGER.debug(session.getRemoteAddress().getHostString() + " closed!");
        service.removeClient(session);
    }

}
