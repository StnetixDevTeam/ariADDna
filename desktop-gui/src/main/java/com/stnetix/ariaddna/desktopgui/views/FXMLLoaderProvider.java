package com.stnetix.ariaddna.desktopgui.views;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * FXML loader provider
 * create custom FXML loader from Spring context
 *
 * @author slonikmak
 */
@Component
public class FXMLLoaderProvider {
    private AnnotationConfigApplicationContext ctx;

    /**
     * Inject
     * @param ctx
     */
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
