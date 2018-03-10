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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.desktopgui.views.FileItem;

/**
 * Controller for file browser
 *
 * @author slonikmak
 */
@Component
public class FileBrowserController implements IGuiController, Initializable {
    @FXML
    private StackPane container;

    /**
     * Native init method, run after FXML field injection
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showContent();
    }

    /**
     * Method for generate file items for fileBrowser and add it into container(temporary realization)
     */
    private void showContent() {
        ObservableList<FileItem> list = FXCollections.observableArrayList();
        GridView<FileItem> myGrid = new GridView<>(list);
        myGrid.setCellFactory(gridView -> new GridCell<FileItem>() {
            @Override
            public void updateItem(FileItem item, boolean empty) {
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //setText(item.getName());
                    setGraphic(item);
                }

            }
        });
        list.addAll(new FileItem("icon", "Folder1"),
                new FileItem("icon", "Documents"),
                new FileItem("icon", "WorkFiles"),
                new FileItem("icon", "Projects"));
        container.getChildren().add(myGrid);
    }

}
