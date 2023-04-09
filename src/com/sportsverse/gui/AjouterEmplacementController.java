/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Emplacement_choix;
import com.sportsverse.service.EmplacementService;
import com.sportsverse.service.Emplacement_choixService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterEmplacementController implements Initializable {

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
    private Button btn_Ajouter;
    @FXML
    private ComboBox<String> ListGovernorat;
    @FXML
    private ComboBox<String> ListDelegation;
    @FXML
    private ComboBox<String> TypeEmplacement;
    @FXML
    private TextField Adresse;
    Emplacement_choixService ep = new Emplacement_choixService();
    private String[] Type = {"Maison","Park","Salle de sport"};
    @FXML
    private ComboBox<String> ListLocalite;
    EmplacementService epS = new EmplacementService();
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Emplacement_choix> emplacements = ep.afficher();
        String[] governorats = new String[emplacements.size()];
        String[] delegations = new String[emplacements.size()];
        String[] localites = new String[emplacements.size()];
        for (int i = 0; i < emplacements.size(); i++) {
            governorats[i] = emplacements.get(i).getGovernorat();
            delegations[i] = emplacements.get(i).getDelegation();
            localites[i] = emplacements.get(i).getLocalite();
        }
        ListGovernorat.getItems().addAll(governorats);
        ListDelegation.getItems().addAll(delegations);
        ListLocalite.getItems().addAll(localites);
        TypeEmplacement.getItems().addAll(Type);
        
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
    private void RedirReserverS(ActionEvent event) {
    }

    @FXML
    private void RedirSuiviS(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        String gov = ListGovernorat.getValue();
        String delg = ListDelegation.getValue();
        String loc = ListLocalite.getValue();
        String typ = TypeEmplacement.getValue();
        String add = Adresse.getText();
        Emplacement emp = new Emplacement(gov,delg,typ,add,loc);
        epS.ajouter(emp);
                FXMLLoader loder = new FXMLLoader(getClass().
                getResource("ReserverSeance.fxml"));
        try {
            root = FXMLLoader.load(getClass().getResource("ReserverSeance.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
