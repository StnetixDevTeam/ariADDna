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
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.desktopgui.views.CloudSettingsFactory;
import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.SettingsViewFactory;
import com.stnetix.ariaddna.desktopgui.views.SimpleTreeElement;
import com.stnetix.ariaddna.desktopgui.views.TreeViewFactory;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;

/**
 * Controller for left pane settings view
 */
@Component
public class LeftPaneSettingsController implements IGuiController, Initializable {
    private MainController mainController;
    private FXMLLoaderProvider loaderProvider;
    private TreeViewFactory treeViewFactory;
    private CloudSettingsFactory cloudSettingsFactory;

    @FXML
    AnchorPane treeViewContainer;

    /**
     * Method for return to Browser view
     *
     * @throws IOException
     */
    @FXML
    public void showBrowser() throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(loaderProvider));
        mainController
                .setCenterBorderContent(SettingsViewFactory.FILE_BROWSER.getNode(loaderProvider));
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
     * @param loaderProvider
     */
    @Autowired
    public void setLoaderProvider(FXMLLoaderProvider loaderProvider) {
        this.loaderProvider = loaderProvider;
    }

    /**
     * Inject treeViewFactory for generate left tree view of settings
     * @param treeViewFactory
     */
    @Autowired
    public void setTreeViewFactory(TreeViewFactory treeViewFactory) {
        this.treeViewFactory = treeViewFactory;
    }

    /**
     * Inject cloudSettingsFactory for generate cloud settings Pane
     * @param cloudSettingsFactory
     */
    @Autowired
    public void setCloudSettingsFactory(CloudSettingsFactory cloudSettingsFactory) {
        this.cloudSettingsFactory = cloudSettingsFactory;
    }

    /**
     * Native init method.
     * Generate left tree view with settings tree menu and add listener of select menu item
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeView<SimpleTreeElement> tree = treeViewFactory.getSettingsTree();
        tree.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        String value = newValue.getValue().getName();
                        if (!newValue.getParent().getValue().getName().equals("root")) {
                            try {
                                mainController.setCenterBorderContent(cloudSettingsFactory
                                        .getNode(value.toUpperCase().replace(" ", "_"),
                                                loaderProvider));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                mainController.setCenterBorderContent(
                                        SettingsViewFactory.valueOf(value.toUpperCase())
                                                .getNode(loaderProvider));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

        treeViewContainer.getChildren().add(tree);
    }
}
