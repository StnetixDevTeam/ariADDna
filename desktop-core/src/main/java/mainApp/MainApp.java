package mainApp;

import configs.CoreConfig;
import guiApp.GuiApp;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Ui;

public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(CoreConfig.class);
        Ui ui = ctx.getBean(Ui.class);
        ui.startUi(args);
    }
}
