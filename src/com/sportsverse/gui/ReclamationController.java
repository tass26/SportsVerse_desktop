/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Reclamation;
import com.sportsverse.services.ReclamationService;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.sportsverse.services.UserService;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField iddescription;
    @FXML
    private Button boutonajouterreclamation;
    @FXML
    private TextField idsujet; 
    ReclamationService sr=new ReclamationService();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button Accueil;
    @FXML
    private Button Produit;
    @FXML
    private Button Panier;
    @FXML
    private Button ReserverSeance;
    @FXML
    private Button SuiviSeance;
    @FXML
    private Button SuiviSeance1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
   
            public String controleDeSaisie(){
        
        String erreur="";
        if(iddescription.getText().trim().isEmpty()){
            erreur+="-descriptiona vide\n";
        }
        if(idsujet.getText().trim().isEmpty()){
            erreur+="-sujet vide\n";
        }
        
      
        return erreur;
    }

    @FXML
    private void ajouter(javafx.event.ActionEvent event) throws SQLException {
         String erreur=controleDeSaisie();
        if(erreur.length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("invalide");
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        else{
            Reclamation  r =new Reclamation();
            r.setNom_client(UserService.getCurrentUser().getNom());
            r.setDescription(iddescription.getText());
            r.setDate(Date.from(ZonedDateTime.now().toInstant()));
            r.setEtat("en cours");
            r.setid_user(UserService.getCurrentUser().getId());
            r.setSujet(idsujet.getText());
            sr.ajouterR(r);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Reclamation Ajoutée");
        if(UserService.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")){
                    try {
            root = FXMLLoader.load(getClass().getResource("ListCoach.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else{
                    try {
            root = FXMLLoader.load(getClass().getResource("LsitDemande.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

    @FXML
    private void Accueil(MouseEvent event) {
        if(UserService.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")){
                    try {
            root = FXMLLoader.load(getClass().getResource("ListCoach.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else{
                    try {
            root = FXMLLoader.load(getClass().getResource("ListDemande.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

    @FXML
    private void RedirReserver(MouseEvent event) {
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

    @FXML
    private void RedirSuivi(MouseEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    
}
