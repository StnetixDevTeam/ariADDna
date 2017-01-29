package com.stnetix.ariaddna.desktopcore.configs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("/com/stnetix/ariaddna/desktopgui")
@ComponentScan("/com/stnetix/ariaddna/commonutils")
public class CoreConfig{


}
