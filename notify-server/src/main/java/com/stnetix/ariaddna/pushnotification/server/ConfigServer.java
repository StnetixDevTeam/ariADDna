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
    public UserService userServiceImpl() {
        return new UserServiceImpl<JsonObject>();
    }

    @Bean
    public AriaddnaWebSocketCreator webSocketCreator() {
        return new AriaddnaWebSocketCreator(userServiceImpl());
    }

    @Bean
    @Scope("singleton")
    public SharedQueue<JsonObject> queue() {
        return new SharedQueue<>();
    }

    @Bean
    public MessageProcessor<JsonObject> processor() {
        return new ServerMessageProcessor();
    }

    @Bean
    public IPushConsume<JsonObject> consumer() {
        return new PushConsumeServerImpl(queue(), processor());
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
        return new ServerMessageSystem(producer(), consumer());
    }

}


