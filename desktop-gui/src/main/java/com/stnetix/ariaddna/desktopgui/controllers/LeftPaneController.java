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

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;

/**
 * Controller for left pane(not expanded) main window(not used in version 1)
 *
 * @author slonikmak
 */
@Component
public class LeftPaneController implements IGuiController {
    private MainController mainController;
    private FXMLLoaderProvider provider;

    /**
     * Show expanded left pane
     * @param e ActionEvent
     * @throws IOException
     */
    @FXML
    public void showTreeView(ActionEvent e) throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(provider));
    }

    /**
     * Inject MainController
     * @param mainController
     */
    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Inject FXML loader provider
     * @param provider
     */
    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }
}
