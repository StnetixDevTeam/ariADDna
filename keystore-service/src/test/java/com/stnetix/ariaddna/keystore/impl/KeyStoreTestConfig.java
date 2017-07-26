package com.stnetix.ariaddna.keystore.impl;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.utils.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexkotov on 25.07.17.
 */
@Configuration
public class KeyStoreTestConfig {
    @Bean
    public IKeyStore keyStore(){
        return new KeyStoreService();
    }

    @Bean
    public IPersistHelper persistHelper(){
        return new PersistHelperStubImpl();
    }

    @Bean
    public CertFactory certFactory(IPersistHelper persistHelper){
        return new CertFactory(persistHelper);

    }

    @Bean
    public KeyFactory keyFactory(IPersistHelper persistHelper, CertFactory certFactory){
        return new KeyFactory(persistHelper, certFactory);
    }

}
