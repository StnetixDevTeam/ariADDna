package com.stnetix.ariaddna.persistence.utils;

import com.stnetix.ariaddna.commonutils.xmlparser.XmlParser;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import com.stnetix.ariaddna.commonutils.xmlparser.handlers.StorageDBHandler;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.services.CertificateServiceImpl;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import com.stnetix.ariaddna.persistence.services.KeyStorePasswordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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

}
