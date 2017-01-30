package com.stnetix.ariaddna.desktopgui.guiApp;

import com.stnetix.ariaddna.commonutils.ui.interfaces.IUi;
import com.stnetix.ariaddna.desktopgui.controllers.GuiController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class GuiApp extends Application implements IUi {


    private static ApplicationContext ctx;


    public void start(final Stage primaryStage) throws Exception {
        //final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(GuiConfig.class);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stentix/ariaddna/desktopgui/fxmlViews/main.fxml"));
        loader.setControllerFactory(param -> {
            GuiController controller = (GuiController) ctx.getBean(param);
            controller.setPrimaryStage(primaryStage);
            return controller;
        });
        Parent parent = loader.load();
        Scene scene = new Scene(parent, 800,600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void startUi(String[] args){
        Application.launch(this.getClass(), args);
    }

    @PreDestroy
    public void stopUi() {
        Platform.exit();
    }

    //javaFX native init application method
    public void init(){

    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
