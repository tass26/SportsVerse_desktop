/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.User;
import com.sportsverse.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author U16
 */
public class ListUserController implements Initializable {

    private TableView<User> xtable;
    private TableColumn<User, Integer> xid;
    private TableColumn <User, String>xnom;
    private TableColumn<User, String> xprenom;
    private TableColumn <User, String> xadresse;
    private TableColumn<User, String> xtel;
    private TableColumn<User, String> xemail;
    private TableColumn<User, String>xrole;
    private TextField Mnom;
    private TextField Mprenom;
    private TextField Madresse;
    private TextField Mtel;
    private TextField Memail;
    @FXML
    private TextField filterField;
    @FXML
    private Button gotoEchangeBtn;
    @FXML
    private Button gotoEchangeBtn1;
    @FXML
    private Button gotoEchangeBtn111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xid.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        xnom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        xprenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        xadresse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
        xtel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNum_tel()));
        xemail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        xrole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        
        UserService crud = new UserService();
        // Populate the table with data
        List<User> data;

            data = crud.afficherUsers();
            xtable.getItems().setAll(data);
            xtable.setRowFactory(tv -> {
		     TableRow<User> myRow = new TableRow<>();
		     myRow.setOnMouseClicked ((event) -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            int myIndex =  xtable.getSelectionModel().getSelectedIndex();

		           int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
		            Mnom.setText(xtable.getItems().get(myIndex).getNom());
                            Mprenom.setText(xtable.getItems().get(myIndex).getPrenom());
                            Madresse.setText(xtable.getItems().get(myIndex).getAdresse());
                            Mtel.setText(xtable.getItems().get(myIndex).getNum_tel());
                            Memail.setText(xtable.getItems().get(myIndex).getEmail());
                           
                         
                           
		        }
		     });
		        return myRow;
                   });
        
    }  
     @FXML
    private void supprimer(ActionEvent event) {
        UserService crud = new UserService();
    User user = xtable.getSelectionModel().getSelectedItem();

    if (user != null) {
        int id = user.getId();
        crud.supprimerUser(id);
        xtable.getItems().remove(user);
    }}
    @FXML
    private void modifier_User(ActionEvent event) {
         UserService crud = new UserService();
        
        int myIndex = xtable.getSelectionModel().getSelectedIndex();
        int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
            String nom = Mnom.getText();
            String prenom = Mprenom.getText();
            String adresse = Madresse.getText();
            String tel = Mtel.getText();
            String email = Memail.getText();
           
      
            crud.modifierUser(id,nom,prenom,adresse,tel,email);
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
