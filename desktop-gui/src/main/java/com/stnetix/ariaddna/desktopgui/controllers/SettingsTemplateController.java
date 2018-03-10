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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import org.springframework.stereotype.Component;

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
    public void setHeaders(String headerStr, String historyPathStr) {
        header.setText(headerStr);
        historyPath.setText(historyPathStr);
    }

    /**
     * Add content(some settings view) to #content container into settings template
     * @param addedContent
     */
    public void setContent(Pane addedContent) {
        content.getChildren().addAll(addedContent);
    }

}
