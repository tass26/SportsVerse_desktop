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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField idnom;
    @FXML
    private TextField idsujet; 
    @FXML
   private TextField idetat;
    @FXML
    private TextField iduser; 
    ReclamationService sr=new ReclamationService();
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
   
            public String controleDeSaisie(){
        
        String erreur="";
     
        if(idnom.getText().trim().isEmpty()){
            erreur+="-nom vide\n";
        }
        if(iddescription.getText().trim().isEmpty()){
            erreur+="-descriptiona vide\n";
        }
        if(iduser.getText().trim().isEmpty()){
            erreur+="-id_user vide\n";
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
            r.setNom_client(idnom.getText());
            r.setDescription(iddescription.getText());
            r.setEtat("en cours");
            r.setid_user(Integer.valueOf(iduser.getText()));
            r.setSujet(idsujet.getText());
            sr.ajouterR(r);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Reclamation Ajoutée");
    }
    
}
