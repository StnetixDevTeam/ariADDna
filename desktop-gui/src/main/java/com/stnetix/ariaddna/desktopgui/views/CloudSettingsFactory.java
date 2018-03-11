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

import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.desktopgui.controllers.SettingsTemplateController;

/**
 * Class for generate each cloud settings page
 *
 * @author slonikmak
 */
@Component
public class CloudSettingsFactory {

    /**
     * Temporary list of clouds
     */
    enum Clouds {
        DROPBOX("Dropbox", "Dropbox"), GOOGLE_DRIVE("GoogleDrive", "Google Drive");

        String name;
        String header;

        Clouds(String name, String header) {
            this.name = name;
            this.header = header;
        }
    }

    /**
     * Generate cloud settings page
     * @param value name of cloud
     * @param loaderProvider FXML loader
     * @return cloud settings page
     * @throws IOException
     */
    public Node getNode(String value, FXMLLoaderProvider loaderProvider) throws IOException {
        FXMLLoader fxmlLoader = loaderProvider
                .get("/com/stentix/ariaddna/desktopgui/fxmlViews/settingsTemplate.fxml");
        Pane parent = fxmlLoader.load();

        Clouds elem = Clouds.valueOf(value);

        SettingsTemplateController controller = fxmlLoader.getController();
        controller.setHeaders(elem.header, elem.name);
        controller.setContent(loaderProvider
                .get("/com/stentix/ariaddna/desktopgui/fxmlViews/cloudSettingsView.fxml").load());
        return parent;
    }
}
