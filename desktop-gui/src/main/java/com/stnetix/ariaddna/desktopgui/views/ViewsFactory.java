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

import javafx.scene.Node;

/**
 * Left pane views factory
 *
 * @author slonikmak
 */
public enum ViewsFactory {
    MAIN("/com/stentix/ariaddna/desktopgui/fxmlViews/main.fxml"),
    LEFT_TREE("/com/stentix/ariaddna/desktopgui/fxmlViews/treeViewLeftPane.fxml"),
    LEFT_MAIN("/com/stentix/ariaddna/desktopgui/fxmlViews/mainLeftPane.fxml"),
    LEFT_SETTINGS("/com/stentix/ariaddna/desktopgui/fxmlViews/settingsLeftPane.fxml");

    public String path;

    ViewsFactory(String path) {
        this.path = path;
    }

    /**
     *
     * @param loader
     * @return node
     * @throws IOException
     */
    public Node getNode(FXMLLoaderProvider loader) throws IOException {
        return loader.get(path).load();
    }
}
