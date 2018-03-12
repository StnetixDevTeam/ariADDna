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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.commonutils.xmlparser.XmlParser;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import com.stnetix.ariaddna.commonutils.xmlparser.handlers.XmlDbSettingHandler;

/**
 * Created by alexkotov on 30.05.17.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.stnetix.ariaddna.persistence.repositories")
public class JPAConfiguration {
    private static AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(JPAConfiguration.class);
    private String dialect = null;

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        XmlDbSettingHandler handler = null;
        File file = null;
        try {
            String resource = "settings.xml";
            URL settingURL = getClass().getClassLoader().getResource(resource);
            if (settingURL.toString().startsWith("jar:")) {
                OutputStream out = null;
                try (InputStream input = getClass().getClassLoader()
                        .getResourceAsStream(resource)) {
                    file = File.createTempFile("tempfile", ".tmp");
                    out = new FileOutputStream(file);
                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = input.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    handler = (XmlDbSettingHandler) new XmlParser(file, new XmlDbSettingHandler())
                            .getHandler();
                    file.deleteOnExit();
                } catch (FileNotFoundException e) {
                    LOGGER.error("Problem with create DataSource bean. Nested exception is: ", e);
                } catch (IOException e) {
                    LOGGER.error("Problem with create DataSource bean. Nested exception is: ", e);
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            LOGGER.error("Problem with close output stream. Nested exception is: ",
                                    e);
                        }
                    }
                }
            } else {
                file = new File(settingURL.getFile());
                handler = (XmlDbSettingHandler) new XmlParser(file, new XmlDbSettingHandler())
                        .getHandler();
            }
        } catch (XmlParserException e) {
            LOGGER.error("Creation of XmlParser object throws exception: ", e);
        }
        if (handler != null) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(handler.getDriverClass());
            config.setJdbcUrl(handler.getUrl());
            config.setUsername(handler.getLogin());
            config.setPassword(handler.getPass());
            dialect = handler.getDialect();
            config.setMaximumPoolSize(5);
            return new HikariDataSource(config);
        }
        return null;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.stnetix.ariaddna.persistence.entities");
        factory.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.put("hibernate.default_schema", "public");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.put("hibernate.dialect", dialect);

        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        LOGGER.info("Entity manager was created in entityManager() bean with name: {} ",
                entityManager.toString());
        return entityManager;
    }

}
