/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.desktopgui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    void setCenterBorderContent(Node node) {
        centerBorder.getChildren().clear();
        centerBorder.getChildren().add(node);
    }
}
