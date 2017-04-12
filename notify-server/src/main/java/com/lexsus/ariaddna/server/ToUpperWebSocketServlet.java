package com.lexsus.ariaddna.server;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name= "My test WS", urlPatterns="/echo")
public class ToUpperWebSocketServlet  extends WebSocketServlet{

    private ServerMessageSystem serverMessageSystem;
    private ApplicationContext applicationContext;

    public ToUpperWebSocketServlet() {
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
        factory.getPolicy().setIdleTimeout(100000);
        applicationContext =
                new AnnotationConfigApplicationContext(ConfigServer.class);
        serverMessageSystem = applicationContext.getBean(ServerMessageSystem.class);
        serverMessageSystem.runMessagesSystem();
        MyAdvancedEchoCreator socket = applicationContext.getBean(MyAdvancedEchoCreator.class);
        SharedQueue queue1 = applicationContext.getBean(SharedQueue.class);
        SharedQueue queue2 = applicationContext.getBean(SharedQueue.class);
        factory.setCreator(socket);

    }
}
