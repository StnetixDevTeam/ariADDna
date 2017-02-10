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

/**
 * Controller for left pane settings view
 */
@Component
public class LeftPaneSettingsController implements IGuiController, Initializable{
    private MainController mainController;
    private FXMLLoaderProvider loaderProvider;
    private TreeViewFactory treeViewFactory;
    private CloudSettingsFactory cloudSettingsFactory;


    @FXML
    AnchorPane treeViewContainer;

    /**
     * Method for return to Browser view
     *
     * @throws IOException
     */
    @FXML
    public void showBrowser() throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(loaderProvider));
        mainController.setCenterBorderContent(SettingsViewFactory.FILE_BROWSER.getNode(loaderProvider));
    }

    /**
     * Inject MainController
     * @param mainController
     */
    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Inject FXML loader provider
     * @param loaderProvider
     */
    @Autowired
    public void setLoaderProvider(FXMLLoaderProvider loaderProvider) {
        this.loaderProvider = loaderProvider;
    }

    /**
     * Inject treeViewFactory for generate left tree view of settings
     * @param treeViewFactory
     */
    @Autowired
    public void setTreeViewFactory(TreeViewFactory treeViewFactory) {
        this.treeViewFactory = treeViewFactory;
    }

    /**
     * Inject cloudSettingsFactory for generate cloud settings Pane
     * @param cloudSettingsFactory
     */
    @Autowired
    public void setCloudSettingsFactory(CloudSettingsFactory cloudSettingsFactory) {
        this.cloudSettingsFactory = cloudSettingsFactory;
    }

    /**
     * Native init method.
     * Generate left tree view with settings tree menu and add listener of select menu item
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeView<SimpleTreeElement> tree = treeViewFactory.getSettingsTree();
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                String value = newValue.getValue().getName();
                if (!newValue.getParent().getValue().getName().equals("root")){
                    try {
                        mainController.setCenterBorderContent(cloudSettingsFactory.getNode(value.toUpperCase().replace(" ","_"), loaderProvider));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mainController.setCenterBorderContent(SettingsViewFactory.valueOf(value.toUpperCase()).getNode(loaderProvider));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        treeViewContainer.getChildren().add(tree);
    }
}
