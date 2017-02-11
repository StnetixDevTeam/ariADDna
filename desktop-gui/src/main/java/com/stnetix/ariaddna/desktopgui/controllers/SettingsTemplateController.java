package com.stnetix.ariaddna.desktopgui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for settings template view
 *
 * @author slonikmak
 */
@Component
public class SettingsTemplateController implements IGuiController, Initializable {

    @FXML
    private Label historyPath;

    @FXML
    private Label header;

    @FXML
    private StackPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Set header and path string to settings template
     * @param headerStr
     * @param historyPathStr
     */
    public void setHeaders(String headerStr, String historyPathStr){
        header.setText(headerStr);
        historyPath.setText(historyPathStr);
    }

    /**
     * Add content(some settings view) to #content container into settings template
     * @param addedContent
     */
    public void setContent(Pane addedContent){
        content.getChildren().addAll(addedContent);
    }


}
