package guiApp;

import configs.GuiConfig;
import controllers.GuiController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import ui.Ui;

import javax.annotation.PreDestroy;

@Component
public class GuiApp extends Application implements Ui {
    public void start(final Stage primaryStage) throws Exception {
        final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(GuiConfig.class);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlViews/main.fxml"));
        loader.setControllerFactory(param -> {
            GuiController controller = (GuiController) ctx.getBean(param);
            controller.setPrimaryStage(primaryStage);
            return controller;
        });
        Parent parent = loader.load();
        Scene scene = new Scene(parent, 300,300);
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


}
