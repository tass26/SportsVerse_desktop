/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.User;
import com.sportsverse.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListCoachController implements Initializable {

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
    private HBox CoachList;
    UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<User> users = new ArrayList<>();
        users = us.getCoachs();
        for(int i=0; i<users.size(); i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoachCard.fxml"));
            try {
                AnchorPane p = loader.load();
                CoachCardController C = loader.getController();
                C.setData(users.get(i));
                CoachList.getChildren().add(p);
            } catch (IOException ex) {
                Logger.getLogger(ListCoachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Retrieve data from the database
        // ...
        // Loop through the data and create ImageView, Label, and Button elements
        /*        for (int i = 0; i < data.size(); i++) {
            String imageUrl = data.get(i).getImageUrl();
            String labelText = data.get(i).getLabelText();
            String buttonText = data.get(i).getButtonText();

            // Create the UI elements
            ImageView imageView = new ImageView(new Image(imageUrl));
            Label label = new Label(labelText);
            Button button = new Button(buttonText);

            // Add the elements to the VBox container
            CoachList.getChildren().addAll(imageView, label, button);
        }*/
    }    
    
}
