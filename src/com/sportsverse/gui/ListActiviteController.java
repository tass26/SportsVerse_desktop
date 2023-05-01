/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Activite;
import com.sportsverse.services.ActiviteService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Achref
 */
public class ListActiviteController implements Initializable {

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
    private Button btn_ajouter;
    @FXML
    private TableView<Activite> table_activites;
    @FXML
    private TableColumn<Activite, String> t_nom;
    @FXML
    private TableColumn<Activite, String> t_desc;
    @FXML
    private TableColumn<Activite, String> t_type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t_nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        t_desc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        t_type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        ActiviteService as = new ActiviteService();
        
        List<Activite> data;
        
        data = as.afficher();
        table_activites.getItems().setAll(data);
        
        
        
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
    private void Ajouter(ActionEvent event) {
    }
    
}
