package com.stnetix.ariaddna.pushnotification.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by LugovoyAV on 15.05.2017.
 */
public class PushServer {
    private int port;
    public PushServer(int port) {
        this.port = port;
    }
    public void start() throws Exception {
        Server server = new Server(port);
        ServletContextHandler scHandler = new ServletContextHandler(server,"/");
        scHandler.addServlet(ArriaddnaWebSocketServlet.class, "/");
        server.start();
        server.join();
    }

}
