package com.stnetix.ariaddna.desktopcore.mainApp;

import com.stnetix.ariaddna.commonutils.ui.interfaces.Ui;
import com.stnetix.ariaddna.desktopcore.configs.CoreConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(CoreConfig.class);
        Ui ui = ctx.getBean(Ui.class);
        ui.startUi(args);
    }
}
