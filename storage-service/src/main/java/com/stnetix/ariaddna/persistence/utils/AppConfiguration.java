package com.stnetix.ariaddna.persistence.utils;

import com.stnetix.ariaddna.persistence.repositories.AccessTokenRepository;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.repositories.CloudCredentialsRepository;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.services.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import javax.persistence.EntityManager;

/**
 * Created by alexkotov on 10.05.17.
 */
@SpringBootApplication
@EntityScan("com.stnetix.ariaddna.persistence.entities")
@Import(JPAConfiguration.class)
public class AppConfiguration {
    @Autowired
    @Qualifier(value = "entityManager")
    private EntityManager em;

    @Bean
    public ICertificateService certificateServiceImpl(){
        return new CertificateServiceImpl();
    }

    @Bean
    public IKeyStorePasswordService keyStorePasswordServiceImpl(){
        return new KeyStorePasswordServiceImpl();
    }

    @Bean
    public RepositoryFactorySupport repositoryFactorySupport(){
        return new JpaRepositoryFactory(em);
    }

    @Bean CertificateRepository certificateRepository(){
        return repositoryFactorySupport().getRepository(CertificateRepository.class);
    }

    @Bean
    public KeyStorePasswordRepository keyStorePasswordRepository(){
        return repositoryFactorySupport().getRepository(KeyStorePasswordRepository.class);
    }

    @Bean
    public AccessTokenRepository accessTokenRepository(){
        return repositoryFactorySupport().getRepository(AccessTokenRepository.class);
    }

    @Bean
    public AccessTokenServiceImpl accessTokenServiceImpl(){
        return new AccessTokenServiceImpl();
    }

    @Bean
    public CloudCredentialsRepository cloudCredentialsRepository(){
        return repositoryFactorySupport().getRepository(CloudCredentialsRepository.class);
    }

    @Bean
    public CloudCredentialsServiceImpl cloudCredentialsServiceImpl(){
        return new CloudCredentialsServiceImpl();
    }
}
