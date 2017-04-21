package com.lexsus.ariaddna.client;

import com.lexsus.ariaddna.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.json.JsonObject;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
@Configuration
@ComponentScan("com.lexsus.ariaddna")
public class ConfigClient {
    @Bean
    public SharedQueue<JsonObject> queue(){
        return new SharedQueue<>();
    }

    @Bean
    public MessageProcessor<JsonObject> processor() {
        return new ClientMessageProcessor();
    }

    @Bean
    public IPushConsume<JsonObject> consumer() {
        return new PushClientConsumeImpl<>(queue(),  processor());
    }

    @Bean
    public ClientMessageSystem clientSystem() {
        return new ClientMessageSystem(consumer());
    }

    @Bean
    public ClientSocketService clientPushNotification() {return new ClientSocketService("ws://127.0.0.1:8080/echo",queue());}


}
