/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.User;
import com.sportsverse.service.UserService;
import com.sportsverse.tools.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class Interface_inscriptionController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label erreurLabel;
    @FXML
    private GridPane signupform;
    @FXML
    private TextField xnom;
    @FXML
    private TextField xprenom;
    @FXML
    private TextField xemail;
    @FXML
    private PasswordField xpassword;
    @FXML
    private TextField xnum_tel;
    @FXML
    private TextField xadresse;
    @FXML
    private ChoiceBox<String> xrole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xrole.getItems().add("Client");
        xrole.getItems().add("coach");

    }   
     @FXML
    private void add(ActionEvent event) throws SQLException, IOException {
        Connection connection = MaConnection.getInstance().getCnx();
         PreparedStatement updateStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
        updateStatement.setString(1, xemail.getText());
        ResultSet rs = updateStatement.executeQuery();
        int i = 0;
        while (rs.next()) {
            i++;
        }

        if (xnom.getText().isEmpty() || xprenom.getText().isEmpty() || xadresse.getText().isEmpty()|| xnum_tel.getText().isEmpty()|| xemail.getText().isEmpty() || xpassword.getText().isEmpty() || xrole.getValue().isEmpty()) {
            erreurLabel.setText("Tous les champs sont obligatoires.");
        } else if (!xnom.getText().matches("[a-z A-Z]+")) {
            erreurLabel.setText("Le nom est invalide.");
        } else if (!xprenom.getText().matches("[a-z A-Z]+")) {
            erreurLabel.setText("Le prenom  est invalide."); 
        }else if (!xadresse.getText().matches("[a-z A-Z]+")) {
            erreurLabel.setText("L'adresse  est invalide.");
        } else if (!xnum_tel.getText().matches("[0-9]{8}")) {
            erreurLabel.setText("Les numero de telephone doit avoir 8 chiffre");   
        } else if (!xemail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            erreurLabel.setText("L'adresse e-mail est invalide.");
       
        } else if (i > 0) {
            erreurLabel.setText("L'email est utilisé  est invalide");
        } else {
     
        
            String nom = xnom.getText();
            String prenom = xprenom.getText();
            String email = xemail.getText();
            String adresse = xadresse.getText();
            String num_tel = xnum_tel.getText();
            String password = xpassword.getText();
            String Roles=xrole.getValue().toUpperCase();
            
            User u1 = new User(prenom, nom,adresse, num_tel, email, password, "[\"ROLE_"+Roles+"\"]");
            UserService cr = new UserService();
            cr.ajouterUser(u1);///lehne yetajouta personne 
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Client insérée avec succés!");

            alert.show();
                 
        
    }
    }

    
}
