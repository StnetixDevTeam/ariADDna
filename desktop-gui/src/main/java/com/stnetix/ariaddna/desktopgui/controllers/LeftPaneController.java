package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LeftPaneController implements IGuiController {
    private MainController mainController;
    private FXMLLoaderProvider provider;

    @FXML
    public void showTreeView(ActionEvent e) throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(provider));
    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }
}
