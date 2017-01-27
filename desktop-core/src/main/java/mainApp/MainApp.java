package mainApp;

import configs.CoreConfig;
import guiApp.GuiApp;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Ui;

/**
 * Created by Oceanos on 27.01.2017.
 */
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(CoreConfig.class);
        Ui ui = (Ui) ctx.getBean(Ui.class);
        ui.startUi(args);
    }
}
