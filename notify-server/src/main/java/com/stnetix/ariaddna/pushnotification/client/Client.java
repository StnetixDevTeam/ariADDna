package com.stnetix.ariaddna.pushnotification.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigClient.class);
        ClientMessageSystem client = applicationContext.getBean(ClientMessageSystem.class);
        client.runMessagesSystem();
    }

}
