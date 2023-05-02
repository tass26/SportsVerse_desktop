/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author U16
 */
public class SignInController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button xbttn;
    @FXML
    private Label erreurLabel;
    @FXML
    private Label title;
    @FXML
    private GridPane loginForm;
    @FXML
    private TextField xemail;
    @FXML
    private PasswordField xpassword;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private Button xbttn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void connecter(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String email = xemail.getText();
       String password = xpassword.getText();
        if (xemail.getText().isEmpty() || xpassword.getText().isEmpty())  {
            erreurLabel.setText("Tous les champs sont obligatoires.");
        }  else if (!xemail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            erreurLabel.setText("L'adresse e-mail est invalide.");
        }  else {
            UserService cr = new UserService();
        boolean c = cr.signIn(email, password);///lehne yetajouta personne 
        if (c == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Connecté avec succés!");
            alert.show();
            System.out.println(cr.getCurrentUser().getNom() );
            if ((cr.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")) &&(cr.getCurrentUser().getIs_banned().equals("non_bloqué"))){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoach.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else if ((cr.getCurrentUser().getRole().equals("[\"ROLE_CLIENT\"]")) &&(cr.getCurrentUser().getIs_banned().equals("bloque"))){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("blocker.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            else if (cr.getCurrentUser().getRole().equals("[\"ROLE_ADMIN\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("inter_adm_list.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (cr.getCurrentUser().getRole().equals("[\"ROLE_COACH\"]")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDemande.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } 
            

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe ou mail incorrect");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
    }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Interface_inscription.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
       @FXML
    private void retour1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    
}
