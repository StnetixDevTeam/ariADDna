package com.stnetix.ariaddna.desktopgui.views;


import javafx.scene.Node;

import java.io.IOException;

public enum  SettingsViewFactory {
    ACCOUNT("Account", "/com/stentix/ariaddna/desktopgui/fxmlViews/accountSettingsPane.fxml");

    public String name;
    public String path;

    SettingsViewFactory(String name, String path){
        this.name = name;
        this.path = path;
    }

    public Node getNode(FXMLLoaderProvider loader) throws IOException {
        return loader.get(path).load();
    }
}
