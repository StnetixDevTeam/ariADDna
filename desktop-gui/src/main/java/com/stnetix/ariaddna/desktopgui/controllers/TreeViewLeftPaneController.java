package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.TreeViewFactory;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TreeViewLeftPaneController implements IGuiController, Initializable {
    MainController mainController;
    FXMLLoaderProvider provider;
    TreeViewFactory treeViewFactory;

    @FXML
    private AnchorPane treeViewContainer;

    @FXML
    void hidePane(ActionEvent event) throws IOException {

    }

    @FXML
    void showSettings() throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_SETTINGS.getNode(provider));
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public void setTreeViewFactory(TreeViewFactory treeViewFactory) {
        this.treeViewFactory = treeViewFactory;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        treeViewContainer.getChildren().add(treeViewFactory.getSimple());

    }
}
