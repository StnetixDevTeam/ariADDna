package com.stnetix.ariaddna.desktopgui.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckTreeView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SyncSettingsController implements IGuiController, Initializable{
    @FXML
    AnchorPane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<String>("Root");
        root.setExpanded(true);
        CheckBoxTreeItem<String> folder1 = new CheckBoxTreeItem<>("Folder1");
        folder1.getChildren().addAll(
                new CheckBoxTreeItem<String>("MyFoto"),
                new CheckBoxTreeItem<String>("OtherFiles")
        );
        root.getChildren().addAll(
                folder1,
                new CheckBoxTreeItem<String>("Documents"),
                new CheckBoxTreeItem<String>("WorkFiles"),
                new CheckBoxTreeItem<String>("Projects"));

        // Create the CheckTreeView with the data
        final CheckTreeView<String> checkTreeView = new CheckTreeView<>(root);
        checkTreeView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
                System.out.println(checkTreeView.getCheckModel().getCheckedItems());
            }
        });
        checkTreeView.setId("sync-tree-view");
        container.getChildren().add(checkTreeView);
    }
}
