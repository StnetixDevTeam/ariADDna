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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(name = "Ariaddna WS", urlPatterns = "/pushnotification")
public class ArriaddnaWebSocketServlet extends WebSocketServlet {

    private ServerMessageSystem serverMessageSystem;
    private ApplicationContext applicationContext;

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    private int idleTimeout = 100000;

    public ArriaddnaWebSocketServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    public void destroy() {
        serverMessageSystem.setStopped(true);
        super.destroy();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {

        //TODO run when server has connection
        factory.getPolicy().setIdleTimeout(idleTimeout);
        applicationContext =
                new AnnotationConfigApplicationContext(ConfigServer.class);
        serverMessageSystem = applicationContext.getBean(ServerMessageSystem.class);
        serverMessageSystem.runMessagesSystem();
        AriaddnaWebSocketCreator socket = applicationContext
                .getBean(AriaddnaWebSocketCreator.class);
        factory.setCreator(socket);
    }
}
