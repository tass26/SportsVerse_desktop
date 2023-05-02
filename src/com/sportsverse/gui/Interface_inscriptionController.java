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
import java.util.Properties;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
      public void envoyeremail(String mail) {
        String to = mail;
        String from = "sarra.mejri@esprit.tn";
        String password = "223JFT3214";

        String host = "smtp.gmail.com";
        String port = "587";

        // Set up properties for the SMTP connection
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a new Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("confirmation");
            message.setText("Cher(e) client,\n" +
"\n" +
"Nous sommes ravis de vous informer que votre inscription sur [nom du site/plateforme/application] a été validée avec succès. Vous pouvez désormais accéder à votre compte en utilisant vos identifiants de connexion.\n" +
"\n" +
"Nous vous remercions pour votre confiance et nous sommes convaincus que vous trouverez notre service utile et satisfaisant. Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter via notre formulaire de contact ou à l'adresse e-mail suivante : [adresse e-mail de l'assistance client].\n" +
"\n" +
"Nous vous souhaitons une excellente expérience sur notre plateforme.\n" +
"\n" +
"Cordialement,\n" +
"L'équipe rescures ");

            // Send the message
            Transport.send(message);

            System.out.println("Message sent successfully!");

        } catch (MessagingException e) {
            System.out.println("Failed to send message: " + e.getMessage());
        }
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
            envoyeremail(email);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Client insérée avec succés!");

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
