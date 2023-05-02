/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.User;
import com.sportsverse.services.UserService;
import com.sportsverse.tools.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author U16
 */
public class ModifierMdpController implements Initializable {

    @FXML
    private Label erreurLabel;
    @FXML
    private Button xboton;
    @FXML
    private PasswordField xpassword;
    @FXML
    private PasswordField xpassword1;
    @FXML
    private PasswordField xpassword2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          UserService cr = new UserService();
                    User c=cr.getCurrentUser();
    }    
     @FXML
    private void changer(ActionEvent event) throws SQLException, IOException {
          

         UserService cr = new UserService();
         if (xpassword.getText().isEmpty() || xpassword1.getText().isEmpty() || xpassword2.getText().isEmpty()) {
            erreurLabel.setText("Tous les champs sont obligatoires.");
        }  else {
       
        String password =xpassword.getText() ;
        String password1 =xpassword1.getText();
        String password2 =xpassword2.getText() ;
        
        
        if ((password1.equals(password2))) {
        cr.changePassword(cr.getCurrentUser().getEmail(), password, password2);///lehne yetajouta personne 
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("mot de passe modifier");
        alert.show();
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("erreur");
        alert.show();}
        if (cr.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClient.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (cr.getCurrentUser().getRole().equals("[\"ROLE_COACH\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceCoach.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
            }
    }}

    @FXML
    private void retour(ActionEvent event) throws IOException {
        UserService cr = new UserService();
        if (cr.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceClient.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (cr.getCurrentUser().getRole().equals("[\"ROLE_COACH\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceCoach.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
            }
    }

}
