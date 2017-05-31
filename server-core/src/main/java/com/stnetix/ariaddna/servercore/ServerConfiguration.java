package com.stnetix.ariaddna.servercore;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.impl.KeyStoreService;
import com.stnetix.ariaddna.persistence.services.CertificateServiceImpl;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import com.stnetix.ariaddna.persistence.services.KeyStorePasswordServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexkotov on 15.05.17.
 */
@Configuration
public class ServerConfiguration {

    @Bean
    public IKeyStorePasswordService keyStorePasswordServise(){
        return new KeyStorePasswordServiceImpl();
    }
    @Bean
    public ICertificateService certificateService(){
        return new CertificateServiceImpl();
    }

    @Bean
    public IKeyStore keyStore(){
        return new KeyStoreService();
    }

}
