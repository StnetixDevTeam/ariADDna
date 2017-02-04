package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CenterPaneController implements IGuiController {
    private MainController mainController;
    private FXMLLoaderProvider loaderProvider;


    @FXML
    void goBack(ActionEvent event) throws IOException {

    }

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Autowired
    public void setLoaderProvider(FXMLLoaderProvider loaderProvider) {
        this.loaderProvider = loaderProvider;
    }
}
