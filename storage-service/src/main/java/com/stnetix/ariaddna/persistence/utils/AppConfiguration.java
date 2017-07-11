package com.stnetix.ariaddna.persistence.utils;

import com.stnetix.ariaddna.persistence.entities.AccessToken;
import com.stnetix.ariaddna.persistence.repositories.AccessTokenRepository;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.repositories.CloudCredentialsRepository;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.security.Certificate;

/**
 * Created by alexkotov on 10.05.17.
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.stnetix.ariaddna.persistence.entities")
public class AppConfiguration {
    @PersistenceContext
    private EntityManager em;

    @Bean
    public CertificateServiceImpl certificateServiceImpl(){
        return new CertificateServiceImpl();
    }

    @Bean
    public KeyStorePasswordServiceImpl keyStorePasswordServiceImpl(){
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
