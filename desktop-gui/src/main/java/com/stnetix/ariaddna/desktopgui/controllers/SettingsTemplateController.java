package com.stnetix.ariaddna.desktopgui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SettingsTemplateController implements IGuiController, Initializable {

    @FXML
    private Label historyPath;

    @FXML
    private Label header;

    @FXML
    private StackPane content;

    public SettingsTemplateController(){
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setHeaders(String headerStr, String historyPathStr){
        header.setText(headerStr);
        historyPath.setText(historyPathStr);
    }

    public void setContent(Pane addedContent){
        content.getChildren().addAll(addedContent);
    }


}
