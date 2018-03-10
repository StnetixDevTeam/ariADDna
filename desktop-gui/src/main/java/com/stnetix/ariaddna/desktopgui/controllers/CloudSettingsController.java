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

package com.stnetix.ariaddna.desktopgui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.ToggleSwitch;
import org.springframework.stereotype.Component;

/**
 * Controller for each cloud settings page
 *
 * @author slonikmak
 */
@Component
public class CloudSettingsController implements IGuiController, Initializable {
    @FXML
    private ToggleSwitch enableBtn;

    @FXML
    private Label enableLable;

    @FXML
    private Label userNameLable;

    @FXML
    private AnchorPane pieChartPane;

    /**
     * Native init method
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChart();
    }

    /**
     * Generate pipe chart
     */
    private void initChart() {
        PieChart.Data available = new PieChart.Data("Available", 13);
        PieChart.Data used = new PieChart.Data("Used", 25);
        PieChart.Data empty = new PieChart.Data("Empty", 10);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(available, used, empty);
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("");
        chart.setPrefSize(350, 350);
        chart.setLegendVisible(false);
        chart.setStyle("-fx-background-color: none");
        pieChartPane.getChildren().add(chart);

        available.getNode().setStyle("-fx-background-color: #55c4fe");
        used.getNode().setStyle("-fx-background-color: #008287");
        empty.getNode().setStyle("-fx-background-color: #219297");

    }
}
