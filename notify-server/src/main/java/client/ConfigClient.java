package client;

import com.lexsus.ariaddna.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
@Configuration
@ComponentScan("com.lexsus.ariaddna")
public class ConfigClient {
    @Bean
    public SharedQueue<String> queue(){
        return new SharedQueue<>();
    }

    @Bean
    public MessageProcessor<String> processor() {
        return new ClientMessageProcessor();
    }

    @Bean
    public IPushConsume<String> consumer() {
        return new PushClientConsumeImpl<>(queue(),  processor());
    }

    @Bean
    public ClientMessageSystem clientSystem() {
        return new ClientMessageSystem(consumer());
    }

    @Bean
    public ClientSocketService clientPushNotification() {return new ClientSocketService("ws://127.0.0.1:8080/echo",queue());}


}
