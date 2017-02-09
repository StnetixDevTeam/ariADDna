package com.stnetix.ariaddna.desktopgui.views;


import com.stnetix.ariaddna.desktopgui.controllers.SettingsTemplateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;

public enum  SettingsViewFactory {
    ACCOUNT("Account", "accountSettingsPane.fxml", "AriADDna account management"),
    CLOUDS("Clouds", "cloudsSettingsPane.fxml", "Clouds management"),
    SYNC("Sync", "syncSettingsPane.fxml", "Synchronization management"),
    ENCRIPTION("Encription", "encriptionSettingsPane.fxml", "Encription managment"),
    FILE_BROWSER("FileBrowser", "fileBrowser.fxml", "File Browser");

    public String name;
    public String fileName;
    public String header;

    public String fullPath = "/com/stentix/ariaddna/desktopgui/fxmlViews/";
    public String template = "settingsTemplate.fxml";

    SettingsViewFactory(String name, String fileName, String header){
        this.name = name;
        this.fileName = fileName;
        this.header = header;
    }

    public Node getNode(FXMLLoaderProvider loader) throws IOException {
        FXMLLoader fxmlLoader = loader.get(fullPath+template);
        Pane parent = fxmlLoader.load();

        SettingsTemplateController controller = fxmlLoader.getController();
        controller.setHeaders(header, name);
        controller.setContent(loader.get(fullPath+fileName).load());
        return parent;
    }
}
