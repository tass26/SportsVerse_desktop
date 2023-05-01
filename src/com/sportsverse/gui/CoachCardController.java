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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CoachCardController implements Initializable {

    @FXML
    private ImageView Coachimg;
    @FXML
    private Label CoachName;
    @FXML
    private Button btnReserver;
    private Stage stage;
    private Scene scene;
    private Parent root;
    int Cid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void  setData(User u){
        CoachName.setText(u.getNom());
        System.out.println(u.getNom());
        System.out.println(u.getId());
        Cid=u.getId();
    }

    @FXML
    private void Reserver(ActionEvent event) {
                FXMLLoader loder = new FXMLLoader(getClass().
                getResource("ReserverSeance.fxml"));
        try {
            Parent root = loder.load();
            ReserverSeanceController rc = loder.getController();
            rc.CoachId=Cid;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
