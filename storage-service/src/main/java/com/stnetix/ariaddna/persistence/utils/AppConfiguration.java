/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
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

package com.stnetix.ariaddna.persistence.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.persistence.repositories.AccessTokenRepository;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.repositories.CloudCredentialsRepository;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.services.AccessTokenServiceImpl;
import com.stnetix.ariaddna.persistence.services.CertificateServiceImpl;
import com.stnetix.ariaddna.persistence.services.CloudCredentialsServiceImpl;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import com.stnetix.ariaddna.persistence.services.IMetatableService;
import com.stnetix.ariaddna.persistence.services.KeyStorePasswordServiceImpl;
import com.stnetix.ariaddna.persistence.services.MetatableServiceImpl;

/**
 * Created by alexkotov on 10.05.17.
 */

@SpringBootApplication
@EntityScan("com.stnetix.ariaddna.persistence.entities")
@Import(JPAConfiguration.class)
public class AppConfiguration {

    private static AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(AppConfiguration.class);

    @PersistenceContext
    @Qualifier(value = "entityManager")
    private EntityManager em;

    @Bean
    public ICertificateService certificateServiceImpl() {
        return new CertificateServiceImpl();
    }

    @Bean
    public IKeyStorePasswordService keyStorePasswordServiceImpl() {
        return new KeyStorePasswordServiceImpl();
    }

    @Bean
    public RepositoryFactorySupport repositoryFactorySupport() {
        LOGGER.info("In bean repositoryFactorySupport() entity manager is : {}", em.toString());
        return new JpaRepositoryFactory(em);
    }

    @Bean CertificateRepository certificateRepository() {
        return repositoryFactorySupport().getRepository(CertificateRepository.class);
    }

    @Bean
    public KeyStorePasswordRepository keyStorePasswordRepository() {
        return repositoryFactorySupport().getRepository(KeyStorePasswordRepository.class);
    }

    @Bean
    public AccessTokenRepository accessTokenRepository() {
        return repositoryFactorySupport().getRepository(AccessTokenRepository.class);
    }

    @Bean
    public AccessTokenServiceImpl accessTokenServiceImpl() {
        return new AccessTokenServiceImpl();
    }

    @Bean
    public CloudCredentialsRepository cloudCredentialsRepository() {
        return repositoryFactorySupport().getRepository(CloudCredentialsRepository.class);
    }

    @Bean
    public CloudCredentialsServiceImpl cloudCredentialsServiceImpl() {
        return new CloudCredentialsServiceImpl();
    }

    @Bean
    public IMetatableService metatableService() {
        return new MetatableServiceImpl();
    }

}
