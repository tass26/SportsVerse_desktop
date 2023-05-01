/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Activite;
import com.sportsverse.entities.Cv;
import com.sportsverse.entities.User;
import com.sportsverse.services.ActiviteService;
import com.sportsverse.services.CvService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achref
 */
public class ModifierCvController implements Initializable {

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
    private Button ListCoach;
    @FXML
    private TextField txt_certif;
    @FXML
    private TextField txt_desc;
    @FXML
    private Spinner<Integer> sp_tarif;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_nom;
    @FXML
    private Spinner<Integer> sp_duree;
    @FXML
    private Button btn_modifier;

    private ComboBox<Activite> LActivites;
    ActiviteService as = new ActiviteService();
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Activite> activites = as.afficher();
        LActivites.getItems().addAll(activites);
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
    private void RedirCoach(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
        int dur = sp_duree.getValue();
        String certif = txt_certif.getText();
        String desc = txt_desc.getText();
        int tarif = sp_tarif.getValue();
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        User u = new User(10, 0, nom, prenom, "tunis", "22114455", "email@gmail.com", "11442233");
        Cv cv = new Cv(u, certif, desc, tarif, "img.png", dur);
        CvService cs = new CvService();
        cs.update(cv);
    }
    
}
