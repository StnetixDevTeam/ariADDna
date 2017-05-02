package com.stnetix.ariaddna.pushnotification.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client{

    public static void main(String[] args)
    {
        String destUri = "ws://127.0.0.1:8080/pushnotification";
        if (args.length > 0)
        {
            destUri = args[0];
        }
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigClient.class);
        ClientMessageSystem client = applicationContext.getBean(ClientMessageSystem.class);
        client.runMessagesSystem();
    }

}
