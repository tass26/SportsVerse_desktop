/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.services.ReclamationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatsRecController implements Initializable {

    @FXML
    private PieChart statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           int total = 0;
ReclamationService rs = new ReclamationService();



int encours = rs.nbEncours();
int traite = rs.nbTraité();
total = encours + traite;

double prcntFeed = (encours * 100) / total;
double prcntRec = (traite * 100) / total;

ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
      new PieChart.Data("En cours : " +rs.nbEncours() + "reclamation", encours),
        new PieChart.Data("Traité : " + rs.nbTraité() + "reclamation",traite)
);


statistique.setTitle("Etat des demandes");
statistique.setAnimated(true);
statistique.setData(list);
    }    
    
}
