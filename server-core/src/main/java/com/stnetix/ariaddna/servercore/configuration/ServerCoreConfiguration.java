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

package com.stnetix.ariaddna.servercore.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.stnetix.ariaddna.pushnotification.server.PushServer;
import com.stnetix.ariaddna.restapiserver.Swagger2SpringBoot;

/**
 * Created by alexkotov on 18.07.17.
 */

@SpringBootApplication
@Import(Swagger2SpringBoot.class)
public class ServerCoreConfiguration {

    private ServerCoreConfiguration() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Swagger2SpringBoot.class, args);
        PushServer pushServer = new PushServer(8081);
        try {
            pushServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
