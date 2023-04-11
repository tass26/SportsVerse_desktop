/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Reclamation;
import com.sportsverse.services.ReclamationService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierRecController implements Initializable {

    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation, String> csujet;
    @FXML
    private TableColumn<Reclamation, String> cdescription;
    @FXML
    private TableColumn<Reclamation, String> cnom;
    @FXML
    private TableColumn<Reclamation, String> cetat;
    @FXML
    private Button idmodifier;
    @FXML
    private TextField iddescription;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idsujet;
    @FXML
    private TextField iduser;
ReclamationService rs = new ReclamationService();
int index=-1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Reclamation r = new Reclamation();
            r.setid_user(Integer.valueOf(iduser.getText()));
            
            r.setNom_client(idnom.getText());
            r.setSujet(idsujet.getText());
            r.setDescription(iddescription.getText());
            rs.modifierO(r);


               List<Reclamation> reclamations = rs.getAllReclamationsByUserId(Integer.valueOf(iduser.getText()));
                                                          
            ObservableList<Reclamation> olp = FXCollections.observableArrayList(reclamations);
            tvreclamation.setItems(olp);
           csujet.setCellValueFactory(new PropertyValueFactory("sujet"));
           cdescription.setCellValueFactory(new PropertyValueFactory("description"));
            cnom.setCellValueFactory(new PropertyValueFactory("nom_client"));
            cetat.setCellValueFactory(new PropertyValueFactory("etat"));
    }

    @FXML
    private void select(KeyEvent event) {
    }

    @FXML
    private void select(MouseEvent event) {
        index =tvreclamation.getSelectionModel().getSelectedIndex();
        if (index < 0) {
    return;
}
idsujet.setText(csujet.getCellData(index).toString());
iddescription.setText(cdescription.getCellData(index).toString());
idnom.setText(cetat.getCellData(index).toString());

    }

    @FXML
    private void userD(KeyEvent event) {
        try {

            List<Reclamation> reclamations = rs.getAllReclamationsByUserId(Integer.valueOf(iduser.getText()));
                                                          
            ObservableList<Reclamation> olp = FXCollections.observableArrayList(reclamations);
            tvreclamation.setItems(olp);
           csujet.setCellValueFactory(new PropertyValueFactory("sujet"));
           cdescription.setCellValueFactory(new PropertyValueFactory("description"));
            cnom.setCellValueFactory(new PropertyValueFactory("nom_client"));
            cetat.setCellValueFactory(new PropertyValueFactory("etat"));
           
          
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
