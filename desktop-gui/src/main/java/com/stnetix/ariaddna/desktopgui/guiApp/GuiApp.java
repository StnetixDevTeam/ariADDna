package com.stnetix.ariaddna.desktopgui.guiApp;

import com.stnetix.ariaddna.commonutils.ui.interfaces.IUi;
import com.stnetix.ariaddna.desktopgui.configs.GuiConfig;
import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Main class
 *
 * @author slonikmak
 */
@Component
public class GuiApp extends Application implements IUi {


    private static ApplicationContext parentCtx;


    /**
     * Native start method
     * @param primaryStage
     * @throws Exception
     */
    public void start(final Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(GuiConfig.class);
        ctx.getBeanFactory().registerSingleton(primaryStage.getClass().getCanonicalName(), primaryStage);
        ctx.setParent(parentCtx);
        ctx.refresh();

        Parent parent = (Parent) ViewsFactory.MAIN.getNode(ctx.getBean(FXMLLoaderProvider.class));
        Scene scene = new Scene(parent, 800,600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Method for start gui from outside
     * @param args
     */
    public void startUi(String[] args){
        Application.launch(this.getClass(), args);
    }

    /**
     * Method run before close app
     */
    @PreDestroy
    public void stopUi() {
        Platform.exit();
    }

    /**
     * JavaFX native init application method
     */
    public void init(){

    }

    /**
     * Inject parent application context
     * @param applicationContext parent application context
     * @throws BeansException
     */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        parentCtx = applicationContext;
    }
}
