package com.stnetix.ariaddna.desktopgui.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Oceanos on 09.02.2017.
 */
public class FileItem extends VBox {
    private FontAwesomeIconView icon;
    private Label fileName;
    private String name;

    public FileItem(String icon, String fileName) {
        super();
        this.name = fileName;
        this.icon = new FontAwesomeIconView(FontAwesomeIcon.FOLDER_ALT);
        this.icon.setStyleClass("file-item-icon");
        this.fileName = new Label(fileName);
        this.getChildren().addAll(this.icon, this.fileName);
    }

    public String getName() {
        return name;
    }

    public FontAwesomeIconView getIcon() {
        return icon;
    }
}
