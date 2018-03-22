package com.stnetix.ariaddna.blockmanipulation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Lexsus on 24.02.2018.
 */
@SpringBootApplication
@ComponentScan("com.stnetix.ariaddna.blockmanipulation")
public class BMSConfiguration {
    @Bean
    public LocalService localService() {
        return new LocalServiceTest();
    }

    @Bean
    public BlockGenerate blockGenerate() {
        return new BlockGenerate();
    }
}
