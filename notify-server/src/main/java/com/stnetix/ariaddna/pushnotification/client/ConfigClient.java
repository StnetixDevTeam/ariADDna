package com.stnetix.ariaddna.pushnotification.client;

import com.google.gson.JsonObject;
import com.stnetix.ariaddna.pushnotification.server.IPushConsume;
import com.stnetix.ariaddna.pushnotification.server.MessageProcessor;
import com.stnetix.ariaddna.pushnotification.server.SharedQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by LugovoyAV on 14.04.2017.
 */
@Configuration
@ComponentScan("com.stnetix.ariaddna.pushnotification")
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
    public ConfigurationService configurationSrevice(){return new ConfigurationService();}

    @Bean
    public ClientSocketService clientPushNotification() {return new ClientSocketService(configurationSrevice().getPushNotificationServerURL(),queue());}


}
