package guiApp;

import configs.GuiConfig;
import controllers.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;



public class GuiApp extends Application {
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

    public void startGui(String[] args){
        Application.launch(this.getClass(), args);
    }

    //javaFX native init application method
    public void init(){

    }
    ///Test start method
    public static void main(String[] args) {
        GuiApp guiApp = new GuiApp();
        guiApp.startGui(args);
    }






}
