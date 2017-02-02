package com.stnetix.ariaddna.desktopgui.views;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Anton on 02.02.2017.
 */
@Component
public class FXMLLoaderProvider {
    private AnnotationConfigApplicationContext ctx;

    @Autowired
    public void setCtx(AnnotationConfigApplicationContext ctx) {
        this.ctx = ctx;
    }

    public FXMLLoader get(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setControllerFactory(param -> ctx.getBean(param));
        return loader;
    }
}
