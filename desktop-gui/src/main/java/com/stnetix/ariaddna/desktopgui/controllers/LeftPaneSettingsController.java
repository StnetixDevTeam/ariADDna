package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LeftPaneSettingsController implements IGuiController, Initializable{
    MainController mainController;
    FXMLLoaderProvider loaderProvider;
    TreeViewFactory treeViewFactory;

    @FXML
    AnchorPane treeViewContainer;

    @FXML
    public void showBrowser() throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(loaderProvider));
        mainController.setCenterBorderContent(SettingsViewFactory.FILE_BROWSER.getNode(loaderProvider));
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @Autowired
    public void setLoaderProvider(FXMLLoaderProvider loaderProvider) {
        this.loaderProvider = loaderProvider;
    }
    @Autowired
    public void setTreeViewFactory(TreeViewFactory treeViewFactory) {
        this.treeViewFactory = treeViewFactory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeView<SimpleTreeElement> tree = treeViewFactory.getSettingsTree();
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                String value = newValue.getValue().getName();
                try {
                    mainController.setCenterBorderContent(SettingsViewFactory.valueOf(value.toUpperCase()).getNode(loaderProvider));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        treeViewContainer.getChildren().add(tree);
    }
}
