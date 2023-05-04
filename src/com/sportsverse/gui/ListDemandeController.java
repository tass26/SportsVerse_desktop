/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Seance;
import com.sportsverse.entities.User;
import com.sportsverse.services.SeanceService;
import com.sportsverse.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListDemandeController implements Initializable {

    @FXML
    private Button Accueil;
    @FXML
    private Button SuiviSeance;
    @FXML
    private HBox DemandeList;
    private Stage stage;
    private Scene scene;
    private Parent root;
    SeanceService ss= new SeanceService();
    UserService us=new UserService();
    @FXML
    private Button SuiviSeance1;
    @FXML
    private Button SuiviSeance2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Seance> seances = new ArrayList<>();
        seances = ss.afficher();
        for(int i=0; i<seances.size(); i++){
            if ((seances.get(i).getEtat().equals("en attente") == true)&&(seances.get(i).getC().getId()==UserService.getCurrentUser().getId())){
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
    private void RedirDemandes(MouseEvent event) {
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

    @FXML
    private void RedirSuivi(MouseEvent event) {
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
    private void RedirReclamer(MouseEvent event) {
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
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    
}
