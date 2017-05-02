package com.stnetix.ariaddna.pushnotification.server;

import com.google.gson.JsonObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;



/**
 * Created by Lexsus on 01.04.2017.
 */
@Configuration
@ComponentScan("com.stnetix.ariaddna.pushnotification")
public class ConfigServer {

    @Bean()
    public UserService userServiceImpl(){return new UserServiceImpl<JsonObject>();}

    @Bean
    public AriaddnaWebSocketCreator webSocketCreator(){return new AriaddnaWebSocketCreator(userServiceImpl());}

    @Bean
    @Scope("singleton")
    public SharedQueue<JsonObject> queue(){
        return new SharedQueue<>();
    }

    @Bean
    public MessageProcessor<JsonObject> processor() {
        return new ServerMessageProcessor();
    }

    @Bean
    public IPushConsume<JsonObject> consumer() {
        return new PushConsumeServerImpl(queue(),  processor());
    }

    @Bean
    public MessageGenerator<JsonObject> generator() {
        //NOTE: spring will scan dependencies because of ComponentScan and inject them
        return new JSONServerMessageGenerator();
    }

    @Bean
    public IPushProduce<JsonObject> producer() {
        return new PushProduceServerImpl(queue(), generator());
    }

    @Bean
    public ServerMessageSystem serverSystem() {
        return new ServerMessageSystem(producer(),consumer());
    }



}


