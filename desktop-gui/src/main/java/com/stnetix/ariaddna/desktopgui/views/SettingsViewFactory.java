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

package com.stnetix.ariaddna.desktopgui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import com.stnetix.ariaddna.desktopgui.controllers.SettingsTemplateController;

/**
 * Center pane views factory.
 * Generate views for center pane of main view
 *
 * @author slonikmak
 */
public enum SettingsViewFactory {
    ACCOUNT("Account", "accountSettingsPane.fxml", "AriADDna account management"),
    CLOUDS("Clouds", "cloudsSettingsPane.fxml", "Clouds management"),
    SYNC("Sync", "syncSettingsPane.fxml", "Synchronization management"),
    ENCRIPTION("Encription", "encriptionSettingsPane.fxml", "Encription managment"),
    FILE_BROWSER("FileBrowser", "fileBrowser.fxml", "File Browser");

    private String name;
    private String fileName;
    private String header;

    private String fullPath = "/com/stentix/ariaddna/desktopgui/fxmlViews/";
    private String template = "settingsTemplate.fxml";

    SettingsViewFactory(String name, String fileName, String header) {
        this.name = name;
        this.fileName = fileName;
        this.header = header;
    }

    /**
     * Insert into common template generated page
     * @param loader
     * @return Node
     * @throws IOException
     */
    public Node getNode(FXMLLoaderProvider loader) throws IOException {
        FXMLLoader fxmlLoader = loader.get(fullPath + template);
        Pane parent = fxmlLoader.load();

        SettingsTemplateController controller = fxmlLoader.getController();
        controller.setHeaders(header, name);
        controller.setContent(loader.get(fullPath + fileName).load());
        return parent;
    }
}
