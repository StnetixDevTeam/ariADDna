package com.stnetix.ariaddna.desktopgui.views;

import javafx.scene.Node;

import java.io.IOException;

public enum ViewsFactory {
    MAIN("/com/stentix/ariaddna/desktopgui/fxmlViews/main.fxml");

    public String path;

    ViewsFactory(String path) {
        this.path = path;
    }

    public Node getNode(FXMLLoaderProvider loader) throws IOException {
        return loader.get(path).load();
    }
}
