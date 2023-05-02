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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author U16
 */
public class Inter_adm_listController implements Initializable {

    @FXML
    private TextField filterField;
   
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
   
    @FXML
   private TableView<User> xtable;
    @FXML
    private TableColumn<User, Integer> xid;
    @FXML
    private TableColumn <User, String>xnom;
    @FXML
    private TableColumn<User, String> xprenom;
    @FXML
    private TableColumn <User, String> xadresse;
    @FXML
    private TableColumn<User, String> xtel;
    @FXML
    private TableColumn<User, String> xemail;
    @FXML
    private TableColumn<User, String>xrole;
    @FXML
    private Button gotoEchangeBtn;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button SuiviSeance;
    @FXML
    private Button SuiviSeance1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           table(); 
        
    }  
    public void table(){
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
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("client modifier");
            alert.show();
            
            table();
    }
    @FXML
    private void logout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
       @FXML
    private void debloquer(ActionEvent event) {
    UserService cr = new UserService();
                  int myIndex = xtable.getSelectionModel().getSelectedIndex();
  int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));
        cr.deblockage(id);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("cette client est debloquer");
            alert.show();
        table();
    
    }
    @FXML
    private void bloquer(ActionEvent event) {
                  UserService cr = new UserService();
                  int myIndex = xtable.getSelectionModel().getSelectedIndex();
  int id = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getId()));

   
        cr.blockage(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("cette personne est bloquer");
            alert.show();
        table();
    }
    
    
    @FXML
    private void filter(KeyEvent event) {
           UserService crud = new UserService();
         ObservableList<User> filteredPeople = FXCollections.observableArrayList(crud.afficherUsers());
        //    ObservableList<Person> filteredPeople = people.filtered(p -> p.getAge() >= 30 && p.getAge() < 40);  

        ObservableList<User> newdata = filteredPeople.stream()
                .filter(n -> n.getNom().toLowerCase().contains(filterField.getText().toLowerCase())
                || n.getPrenom().toLowerCase().equals(filterField.getText())
                || n.getPrenom().toLowerCase().contains(filterField.getText())
                   ||n.getEmail().toLowerCase().equals(filterField.getText())
                || n.getEmail().toLowerCase().contains(filterField.getText())
                   || n.getAdresse().toLowerCase().equals(filterField.getText())
                || n.getAdresse().toLowerCase().contains(filterField.getText())
                || n.getNom().toLowerCase().contains(filterField.getText().toLowerCase())
                || n.getNom().toLowerCase().equals(filterField.getText()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        xtable.setItems(newdata);
    }

    @FXML
    private void redirProduit(MouseEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RedirRec(MouseEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("AffichageRec.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    
    }

    @FXML
    private void RedirSuivi(MouseEvent event) {

        try {
            root = FXMLLoader.load(getClass().getResource("SuiviSeanceClient.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RedirStat(MouseEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RedirCoach(ActionEvent event) {
    }
    
}
