package com.stnetix.ariaddna.pushnotification.server;

/**
 * Created by LugovoyAV on 19.05.2017.
 */
public class Server {
    public static void main(String[] args) throws Exception {

        PushServer pushServer = new PushServer(8080);
        pushServer.start();    }
}
