package com.stnetix.ariaddna.desktopgui.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Oceanos on 09.02.2017.
 */
public class FileItem extends VBox {
    FontAwesomeIconView icon;
    Label fileName;
    String name;

    public FileItem(String icon, String fileName) {
        super();
        this.name = fileName;
        this.icon = new FontAwesomeIconView(FontAwesomeIcon.FOLDER_ALT);
        this.icon.setStyleClass("file-item-icon");
        this.fileName = new Label(fileName);
        this.getChildren().addAll(this.icon, this.fileName);
        //this.setPrefWidth(90);
        //this.setPadding(new Insets(0, 30, 0, 0));
    }

    public String getName() {
        return name;
    }

    public FontAwesomeIconView getIcon() {
        return icon;
    }
}
