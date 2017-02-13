package com.stnetix.ariaddna.desktopgui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for main view. load views management
 *
 * @author slonikmak
 */
@Component
public class MainController implements IGuiController, Initializable {
    private Stage primaryStage;

    @FXML
    StackPane leftBorder;
    @FXML
    StackPane centerBorder;

    /**
     * Inject primary stage
     */
    @Autowired
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Load node into left pain
     * @param node
     */
    void setLeftBorderContent(Node node) {
        leftBorder.getChildren().clear();
        leftBorder.getChildren().add(node);
    }

    /**
     * Load node into center pane
     * @param node
     */
    void setCenterBorderContent(Node node){
        centerBorder.getChildren().clear();
        centerBorder.getChildren().add(node);
    }
}
