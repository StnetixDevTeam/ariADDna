package com.stnetix.ariaddna.desktopgui.controllers;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class MainController implements GuiController {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
