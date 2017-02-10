package com.stnetix.ariaddna.desktopgui.controllers;

import com.stnetix.ariaddna.desktopgui.views.FXMLLoaderProvider;
import com.stnetix.ariaddna.desktopgui.views.ViewsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Controller for left pane(not expanded) main window(not used in version 1)
 *
 * @author slonikmak
 */
@Component
public class LeftPaneController implements IGuiController {
    private MainController mainController;
    private FXMLLoaderProvider provider;

    /**
     * Show expanded left pane
     * @param e ActionEvent
     * @throws IOException
     */
    @FXML
    public void showTreeView(ActionEvent e) throws IOException {
        mainController.setLeftBorderContent(ViewsFactory.LEFT_TREE.getNode(provider));
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
     * @param provider
     */
    @Autowired
    public void setProvider(FXMLLoaderProvider provider) {
        this.provider = provider;
    }
}
