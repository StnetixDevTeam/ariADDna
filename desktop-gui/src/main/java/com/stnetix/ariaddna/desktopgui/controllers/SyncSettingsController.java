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

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.CheckTreeView;
import org.springframework.stereotype.Component;

/**
 * Controller fore synchronization settings page
 *
 * @author slonikmak
 */
@Component
public class SyncSettingsController implements IGuiController, Initializable {
    @FXML
    AnchorPane container;

    /**
     * Native init method.
     * Create VUFS folders tree view
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: replace to getting elements from Repository
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Root");
        root.setExpanded(true);
        CheckBoxTreeItem<String> folder1 = new CheckBoxTreeItem<>("Folder1");
        folder1.getChildren().addAll(
                new CheckBoxTreeItem<>("MyFoto"),
                new CheckBoxTreeItem<>("OtherFiles")
        );
        root.getChildren().addAll(
                folder1,
                new CheckBoxTreeItem<>("Documents"),
                new CheckBoxTreeItem<>("WorkFiles"),
                new CheckBoxTreeItem<>("Projects"));

        // Create the CheckTreeView with the data
        final CheckTreeView<String> checkTreeView = new CheckTreeView<>(root);
        checkTreeView.getCheckModel().getCheckedItems()
                .addListener((ListChangeListener<TreeItem<String>>) c -> {
                    System.out.println(checkTreeView.getCheckModel().getCheckedItems());
                });
        checkTreeView.setId("sync-tree-view");
        container.getChildren().add(checkTreeView);
    }
}
