/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.desktopgui.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

/**
 * Simple class for present VUFS item
 * @author slonikmak
 * TODO: replace with a real VUFS item class
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
