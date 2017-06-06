package com.stnetix.ariaddna.keystore.config;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.impl.KeyStoreService;
import com.stnetix.ariaddna.persistence.services.CertificateServiceImpl;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import com.stnetix.ariaddna.persistence.services.KeyStorePasswordServiceImpl;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by alexkotov on 01.06.17.
 */
@Configuration
@ComponentScan(basePackages = {"com.stnetix.ariaddna.keystore.impl", "com.stnetix.ariaddna.keystore.utils"})
@Import(AppConfiguration.class)
public class KeyStoreConfig {
    @Bean
    public IKeyStore keyStore(){
        return new KeyStoreService();
    }

}
