/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Emplacement_choix;
import com.sportsverse.entities.Seance;
import com.sportsverse.service.EmplacementService;
import com.sportsverse.service.Emplacement_choixService;
import com.sportsverse.service.SeanceService;
import com.sportsverse.service.UserService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReserverSeanceController implements Initializable {

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
    private DatePicker DatePicker;
    @FXML
    private ComboBox<String> CBDuree;
    @FXML
    private ComboBox<String> CBEmplacemnt;
    @FXML
    private Button btnAjoutEmplacement;
    @FXML
    private TextField txt_mesage;
    @FXML
    private Button btn_reserver;
    EmplacementService ep = new EmplacementService();
    UserService u = new UserService();
    SeanceService ss = new SeanceService();
    private String[] Duree = {"1 heure","2 heure"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CBDuree.getItems().addAll(Duree);
        List<Emplacement> emplacements = ep.afficher();
        String[] emplacementChoix = new String[emplacements.size()];
        for (int i = 0; i < emplacements.size(); i++) {
            emplacementChoix[i] = emplacements.get(i).toString();
        }
        CBEmplacemnt.getItems().addAll(emplacementChoix);
    }    


    @FXML
    private void RedirAccueil(ActionEvent event) {
    }

    @FXML
    private void Login(ActionEvent event) {
    }

    @FXML
    private void RedirProduit(ActionEvent event) {
    }

    @FXML
    private void RedirPanier(ActionEvent event) {
    }

    @FXML
    private void RedirReserver(ActionEvent event) {
    }

    @FXML
    private void RedirSuivi(ActionEvent event) {
    }

    @FXML
    private void Reserver(ActionEvent event) {
        LocalDate dateres = DatePicker.getValue();
        Date d = Date.valueOf(dateres);
        String dur = CBDuree.getValue();
        String Emp = CBEmplacemnt.getValue();
        String Etat = "en attente";
        String msg = txt_mesage.getText();
        String adresse = u.read(1).getAdresse();
        Seance s = new Seance(u.read(1),ep.read(Character.getNumericValue(Emp.charAt(0))),d,Etat,dur,adresse,msg);
        ss.ajouter(s);
    }
    
}
