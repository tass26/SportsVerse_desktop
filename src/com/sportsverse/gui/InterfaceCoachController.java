/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.User;
import com.sportsverse.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarah
 */
public class InterfaceCoachController implements Initializable {

    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mprenom;
    @FXML
    private TextField Madresse;
    @FXML
    private TextField Mtel;
    @FXML
    private TextField Memail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                          UserService cr = new UserService();
                    User c=cr.getCurrentUser();
                      Mnom.setText(c.getNom());
                            Mprenom.setText(c.getPrenom());
                            Madresse.setText(c.getAdresse());
                            Mtel.setText(c.getNum_tel());
                            Memail.setText(c.getEmail());
    }    
      @FXML
    private void modifier_Coach(ActionEvent event) {
        UserService cr = new UserService();
                    User c=cr.getCurrentUser();
        int id = c.getId();
            String nom = Mnom.getText();
            String prenom = Mprenom.getText();
            String adresse = Madresse.getText();
            String tel = Mtel.getText();
            String email = Memail.getText();
           
      
            cr.modifierUser(id,nom,prenom,adresse,tel,email);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Modification affecté avec succée");
            alert.show(); 
            
    } 
    @FXML
    public void modifiermdp(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMdp.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    public void logout(ActionEvent event) throws IOException{
           
        
   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
}
