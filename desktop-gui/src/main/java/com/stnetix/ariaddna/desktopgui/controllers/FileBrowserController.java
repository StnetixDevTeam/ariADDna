package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FileItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.cell.ColorGridCell;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

@Component
public class FileBrowserController implements IGuiController, Initializable {
    @FXML
    private StackPane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showContent();
    }

    private void showContent() {
        ObservableList<FileItem> list = FXCollections.observableArrayList();
        GridView<FileItem> myGrid = new GridView<>(list);
        myGrid.setCellFactory(gridView -> new GridCell<FileItem>(){
            @Override
            public void updateItem(FileItem item, boolean empty){
                if (empty||item==null){
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
