package com.stnetix.ariaddna.persistence.utils;

import com.stnetix.ariaddna.commonutils.xmlparser.XmlParser;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import com.stnetix.ariaddna.commonutils.xmlparser.handlers.XmlDbSettingHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by alexkotov on 30.05.17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.stnetix.ariaddna.persistence.repositories")
public class JPAConfiguration {

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource(){
        XmlDbSettingHandler handler = null;
        try {
            handler = (XmlDbSettingHandler) new XmlParser("./../settings.xml", new XmlDbSettingHandler()).getHandler();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(handler.getDriverClass());
        config.setJdbcUrl(handler.getUrl());
        config.setUsername(handler.getLogin());
        config.setPassword(handler.getPass());
        config.setMaximumPoolSize(5);

        return new HikariDataSource(config);
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
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");

        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

}
