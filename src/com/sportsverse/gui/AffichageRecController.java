/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Reclamation;
//import com.sportsverse.entities.User;
import com.sportsverse.services.ReclamationService;
//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.MouseEvent;

import javax.management.relation.Role;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageRecController implements Initializable {

  
    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, String > csujet;
    @FXML
    private TableColumn<Reclamation, String> cdescription;
    @FXML
    private TableColumn<Reclamation, String> cnom;
    @FXML
    private TableColumn<Reclamation, String> cetat;
    ReclamationService r=new ReclamationService();
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
  public void initialize(URL url, ResourceBundle rb) {
        try {
            refreshList();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichageRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public void refreshList() throws SQLException{
        data.clear();
        data=FXCollections.observableArrayList(r.afficheListeR());
        csujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       
        tvreclamation.setItems(data);
    }
    
  /*  @FXML
    private void modifier(ActionEvent event) {
        if(tvreclamation.getSelectionModel().getSelectedItem()!=null){
            Reclamation r=new Reclamation();
            int id=tvreclamation.getSelectionModel().getSelectedItem().getId();
            r.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setUsername(tfusername.getText());
            u.setPassword(pfmdp.getText());
            u.setAdresse(tfadresse.getText());
            u.setNumTel(Integer.valueOf(tfnumtel.getText()));
            u.setRole(cbrole.getValue());
            u.setDate_naissance(Date.valueOf(dpdate.getValue()));
            u.setRating(Integer.valueOf(tfrating.getText()));
            su.modifier(id,u);
            refreshList();
        }
    }*/

    
    

    @FXML
    private void supprimer(javafx.event.ActionEvent event) throws SQLException {
        if(tvreclamation.getSelectionModel().getSelectedItem()!=null){
            int id=tvreclamation.getSelectionModel().getSelectedItem().getId();
            r.supprimer(id);
            refreshList();
        }

    }

    @FXML
    private void select(MouseEvent event) {
    }

    

   
}
