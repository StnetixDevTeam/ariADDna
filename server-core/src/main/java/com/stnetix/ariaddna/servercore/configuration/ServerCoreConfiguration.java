package com.stnetix.ariaddna.servercore.configuration;

import com.stnetix.ariaddna.pushnotification.server.PushServer;
import com.stnetix.ariaddna.restapiserver.Swagger2SpringBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by alexkotov on 18.07.17.
 */

@SpringBootApplication
@Import(Swagger2SpringBoot.class)
public class ServerCoreConfiguration  {
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
