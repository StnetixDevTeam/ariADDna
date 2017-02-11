package com.stnetix.ariaddna.desktopgui.views;

import javafx.scene.Node;

import java.io.IOException;

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
