package com.stnetix.ariaddna.pushnotification.server;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name= "Ariaddna WS", urlPatterns="/pushnotification")
public class ArriaddnaWebSocketServlet extends WebSocketServlet{

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
        AriaddnaWebSocketCreator socket = applicationContext.getBean(AriaddnaWebSocketCreator.class);
        factory.setCreator(socket);
    }
}
