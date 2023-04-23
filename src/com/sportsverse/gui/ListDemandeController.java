/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Seance;
import com.sportsverse.entities.User;
import com.sportsverse.service.SeanceService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListDemandeController implements Initializable {

    @FXML
    private Button Accueil;
    @FXML
    private Button Login;
    @FXML
    private Button Produit;
    @FXML
    private Button Panier;
    @FXML
    private Button ReserverSeance;
    @FXML
    private Button SuiviSeance;
    @FXML
    private HBox DemandeList;
    SeanceService ss= new SeanceService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Seance> seances = new ArrayList<>();
        seances = ss.afficher();
        for(int i=0; i<seances.size(); i++){
            if (seances.get(i).getEtat().equals("en attente") == false){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SeanceCard.fxml"));
            try {
                AnchorPane p = loader.load();
                SeanceCardController C = loader.getController();
                C.setData(seances.get(i));
                C.seance=seances.get(i);
                DemandeList.getChildren().add(p);
            } catch (IOException ex) {
                Logger.getLogger(ListCoachController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }    
    }
    
}
