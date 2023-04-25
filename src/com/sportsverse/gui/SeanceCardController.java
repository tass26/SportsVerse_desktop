/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.apis.JavaMail;
import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Seance;
import com.sportsverse.entities.User;
import com.sportsverse.service.EmplacementService;
import com.sportsverse.service.SeanceService;
import com.sportsverse.service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SeanceCardController implements Initializable {

    @FXML
    private Label CoachName;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int Sid;
    Seance seance;
    UserService us = new UserService();
    SeanceService ss= new SeanceService();
    EmplacementService ps = new EmplacementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void  setData(Seance s){
        CoachName.setText(us.getUserByAdress(s.getAdresse_client()).getNom());
        Sid=s.getId();
    }

    @FXML
    private void Refuser(MouseEvent event) {
        String rep = "Le coach "+ us.read(seance.getC().getId()).getNom()+" a refusé votre Demande de coaching pour le "+seance.getDate();
        ss.Refuser(seance);
        try
        {
          
             JavaMail.sendMail(seance.getAdresse_client(),rep);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void Accepter(MouseEvent event) {
        String rep = "Le coach "+ us.read(seance.getC().getId()).getNom()+" a Accepter votre Demande de coaching pour le "+seance.getDate();
        
        try
        {
            JavaMail.sendMail(seance.getAdresse_client(),rep);
            ss.Accepter(seance);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void AfficherInfo(MouseEvent event) {
        Emplacement emp = ps.read(seance.getE().getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Demande de Coaching");
        alert.setHeaderText("Informations");
        alert.setContentText("Le client " + us.getUserByAdress(seance.getAdresse_client()).getNom() + " \nSouhaite que vous le coachiez pour le " + seance.getDate() + "\nà " +emp.getAdresse()+" "+ emp.getDelegation()+" , "+emp.getGovernorat());

        // Show the alert and wait for user response
        alert.showAndWait();
    }
    
}
