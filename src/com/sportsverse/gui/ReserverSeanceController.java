/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Emplacement_choix;
import com.sportsverse.entities.Seance;
import com.sportsverse.services.EmplacementService;
import com.sportsverse.services.Emplacement_choixService;
import com.sportsverse.services.SeanceService;
import com.sportsverse.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button SuiviSeance1;
    @FXML
    private Label ControleDate;
    @FXML
    private Label ControleEmp;
    @FXML
    private Label ControleDuree;
    @FXML
    private Label ControleMsg;
    int CoachId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void RedirSuivi(ActionEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("Suiviseance.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Reserver(ActionEvent event) {
        if(DatePicker.getValue() == null){
            ControleDate.setText("Required");
        }
        if(CBDuree.getValue() == null){
            ControleDuree.setText("Required");
        }
        if(CBEmplacemnt.getValue() == null){
            ControleEmp.setText("Required");
        }
        if(txt_mesage.getText() == null){
            ControleMsg.setText("Required");
        }
        LocalDate dateres = DatePicker.getValue();
        Date d = Date.valueOf(dateres);
        String dur = CBDuree.getValue();
        String Emp = CBEmplacemnt.getValue();
        String Etat = "en attente";
        String msg = txt_mesage.getText();
        String adresse = u.read(u.getCurrentUser().getId()).getEmail();
        System.out.println(adresse);
        Seance s = new Seance(u.read(CoachId),ep.read(Character.getNumericValue(Emp.charAt(0))),d,Etat,dur,adresse,msg);
        ss.ajouter(s);
    }

    @FXML
    private void RedirAjoutEmplacement(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("AjouterEmplacement.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RedirClient(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Accueil(MouseEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}