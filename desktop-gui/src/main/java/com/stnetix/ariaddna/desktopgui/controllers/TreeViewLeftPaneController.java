package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
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

    @FXML
    private AnchorPane treeViewContainer;

    @FXML
    void hidePane(ActionEvent event) throws IOException {

    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeView<String> tree = new TreeView<>();
        tree.setShowRoot(false);
        TreeItem<String> root = new TreeItem<>("");
        tree.setRoot(root);

        ChangeListener<Boolean> expandedListener = (obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                ReadOnlyProperty<?> expandedProperty = (ReadOnlyProperty<?>) obs ;
                Object itemThatWasJustExpanded = expandedProperty.getBean();
                for (TreeItem<String> item : tree.getRoot().getChildren()) {
                    if (item != itemThatWasJustExpanded) {
                        item.setExpanded(false);
                    }
                }
            }
        };

        for (int i=1; i<=4; i++) {
            TreeItem<String> item = new TreeItem<>("Top level "+i);
            item.expandedProperty().addListener(expandedListener);
            root.getChildren().add(item);
            for (int j=1; j<=4; j++) {
                TreeItem<String> subItem = new TreeItem<>("Sub item "+i+":"+j);
                item.getChildren().add(subItem);
            }
        }

        PseudoClass subElementPseudoClass = PseudoClass.getPseudoClass("sub-tree-item");

        tree.setCellFactory(tv -> {
            TreeCell<String> cell = new TreeCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisclosureNode(null);

                    if (empty) {
                        setText("");
                        setGraphic(null);
                    } else {
                        setText(item); // appropriate text for item
                    }
                }

            };
            cell.treeItemProperty().addListener((obs, oldTreeItem, newTreeItem) -> {
                cell.pseudoClassStateChanged(subElementPseudoClass,
                        newTreeItem != null && newTreeItem.getParent() != cell.getTreeView().getRoot());
            });
            return cell ;
        });
        treeViewContainer.getChildren().add(tree);

    }
}
