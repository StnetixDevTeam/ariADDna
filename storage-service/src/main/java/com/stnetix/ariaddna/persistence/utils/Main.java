package com.stnetix.ariaddna.persistence.utils;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by alexkotov on 12.09.17.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JPAConfiguration.class);
        ctx.getBean(DataSource.class);
    }
}
