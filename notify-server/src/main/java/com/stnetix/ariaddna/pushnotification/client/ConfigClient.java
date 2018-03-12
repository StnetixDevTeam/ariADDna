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

package com.stnetix.ariaddna.pushnotification.client;

import com.google.gson.JsonObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.stnetix.ariaddna.pushnotification.server.IPushConsume;
import com.stnetix.ariaddna.pushnotification.server.MessageProcessor;
import com.stnetix.ariaddna.pushnotification.server.SharedQueue;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
@Configuration
@ComponentScan("com.stnetix.ariaddna.pushnotification")
public class ConfigClient {
    @Bean
    public SharedQueue<JsonObject> queue() {
        return new SharedQueue<>();
    }

    @Bean
    public MessageProcessor<JsonObject> processor() {
        return new ClientMessageProcessor();
    }

    @Bean
    public IPushConsume<JsonObject> consumer() {
        return new PushClientConsumeImpl<>(queue(), processor());
    }

    @Bean
    public ClientMessageSystem clientSystem() {
        return new ClientMessageSystem(consumer());
    }

    @Bean
    public ConfigurationService configurationSrevice() {
        return new ConfigurationService();
    }

    @Bean
    public ClientSocketService clientPushNotification() {
        return new ClientSocketService(configurationSrevice().getPushNotificationServerURL(),
                queue());
    }

}
