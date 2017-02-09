package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FileItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class FileBrowserController implements IGuiController, Initializable {
    @FXML
    private FlowPane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showContent();
    }

    private void showContent() {
        container.getChildren().addAll(new FileItem("icon", "Folder1"),
                new FileItem("icon", "Documents"),
                new FileItem("icon", "WorkFiles"),
                new FileItem("icon", "Projects"));
    }
}
