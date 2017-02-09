package com.stnetix.ariaddna.desktopgui.views;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.stereotype.Component;

@Component
public class TreeViewFactory {
    public TreeView get(){
        TreeView<String> tree = new TreeView<>();
        tree.setShowRoot(false);
        TreeItem<String> root = new TreeItem<>("");
        tree.setRoot(root);

        ChangeListener<Boolean> expandedListener = (obs, wasExpanded, isNowExpanded) -> {
            if (isNowExpanded) {
                System.out.println("expand");
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

        return tree;
    }

    private TreeItem<SimpleTreeElement> makeBranch(SimpleTreeElement element, TreeItem<SimpleTreeElement> root){
        TreeItem<SimpleTreeElement> newBranch = new TreeItem<>(element);
        root.getChildren().add(newBranch);
        return newBranch;
    }

    public TreeView<SimpleTreeElement> getSimple(){
        TreeView<SimpleTreeElement> tree = new TreeView<>();
        TreeItem<SimpleTreeElement> root = new TreeItem<>(new SimpleTreeElement("root", 0));

        TreeItem<SimpleTreeElement> outer1, outer2, inner1, inner2;
        outer1 = makeBranch(new SimpleTreeElement("Folder1", 1), root);
        outer2 = makeBranch(new SimpleTreeElement("Documents", 2), root);
        makeBranch(new SimpleTreeElement("MyFotos", 3), outer1);
        makeBranch(new SimpleTreeElement("OtherFiles", 4), outer1);
        makeBranch(new SimpleTreeElement("WorkFiles", 5), root);
        makeBranch(new SimpleTreeElement("Projects", 6), root);

        tree.setRoot(root);
        tree.setPrefWidth(200);

        setTreeCellFactory(tree);



        tree.setShowRoot(false);
        return tree;

    }

    public TreeView<SimpleTreeElement> getSettingsTree(){
        TreeView<SimpleTreeElement> tree = new TreeView<>();
        TreeItem<SimpleTreeElement> root = new TreeItem<>(new SimpleTreeElement("root", 0));

        TreeItem<SimpleTreeElement> outer1, outer2, inner1, inner2;
        outer1 = makeBranch(new SimpleTreeElement("Account", 1), root);
        outer2 = makeBranch(new SimpleTreeElement("Clouds", 2), root);
        makeBranch(new SimpleTreeElement("Dropbox", 3), outer2);
        makeBranch(new SimpleTreeElement("Google Drive", 4), outer2);
        makeBranch(new SimpleTreeElement("Sync", 5), root);
        makeBranch(new SimpleTreeElement("Encription", 6), root);

        tree.setRoot(root);
        tree.setPrefWidth(200);

        setTreeCellFactory(tree);



        tree.setShowRoot(false);

        return tree;
    }

    private void setTreeCellFactory(TreeView<SimpleTreeElement> tree){
        tree.setCellFactory(param -> new TreeCell<SimpleTreeElement>() {
            @Override
            public void updateItem(SimpleTreeElement item, boolean empty) {
                super.updateItem(item, empty);
                //setDisclosureNode(null);

                if (empty) {
                    setText("");
                    setGraphic(null);
                } else {
                    setText(item.getName()); // appropriate text for item
                }
            }

        });

        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                System.out.println(newValue.getValue());
            }
        });
    }
}
