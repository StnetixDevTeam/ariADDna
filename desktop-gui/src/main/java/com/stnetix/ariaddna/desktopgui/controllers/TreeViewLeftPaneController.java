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
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.SettingsViewFactory;
import com.stnetix.ariaddna.desktopgui.views.TreeViewFactory;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;

/**
 * Controller for file browser left pane
 *
 * @author slonikmak
 */
@Component
public class TreeViewLeftPaneController implements IGuiController, Initializable {
    MainController mainController;
    FXMLLoaderProvider provider;
    TreeViewFactory treeViewFactory;

    @FXML
    private AnchorPane treeViewContainer;

    /**
     * Load settings pane into left and center pane
     * @throws IOException
     */
    @FXML
    void showSettings() throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_SETTINGS.getNode(provider));
        mainController.setCenterBorderContent(SettingsViewFactory.ACCOUNT.getNode(provider));
    }

    /**
     * inject MainController
     * @param mainController
     */
    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * inject MFXMLLoaderProvider
     * @param provider
     */
    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }

    /**
     * inject TreeViewFactory
     * @param treeViewFactory
     */
    @Autowired
    public void setTreeViewFactory(TreeViewFactory treeViewFactory) {
        this.treeViewFactory = treeViewFactory;
    }

    /**
     * Native init method.
     * Add tree view VUFS items into left pane
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
         * TODO: replace to getting elements from Repository
         */
        treeViewContainer.getChildren().add(treeViewFactory.getSimple());

    }
}
