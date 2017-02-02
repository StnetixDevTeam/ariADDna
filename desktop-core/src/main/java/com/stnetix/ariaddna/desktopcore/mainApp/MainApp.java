package com.stnetix.ariaddna.desktopcore.mainApp;

import com.stnetix.ariaddna.commonutils.ui.interfaces.IUi;
import com.stnetix.ariaddna.desktopcore.configs.CoreConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CoreConfig.class);
        IUi ui = ctx.getBean(IUi.class);
        ui.startUi(args);
    }
}
