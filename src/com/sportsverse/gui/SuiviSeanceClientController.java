/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Seance;
import com.sportsverse.service.SeanceService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SuiviSeanceClientController implements Initializable {

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
    private TableColumn<Seance, String> NomCoach;
    @FXML
    private TableColumn<Seance, String> Emplacement;
    @FXML
    private TableColumn<Seance, Date> Date;
    @FXML
    private TableColumn<Seance, String> Duree;
    @FXML
    private TableColumn<Seance, String> Etat;
    SeanceService ss = new SeanceService();
    ObservableList<Seance> SeanceList = FXCollections.observableArrayList(ss.afficher());
    @FXML
    private TableView<Seance> SeanceTable;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Seance, String> Modifier;
    @FXML
    private Button SuiviSeance1;
    @FXML
    private TableColumn<Seance, String> Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NomCoach.setCellValueFactory(new PropertyValueFactory<Seance,String>("C"));
        Emplacement.setCellValueFactory(new PropertyValueFactory<Seance,String>("E"));
        Date.setCellValueFactory(new PropertyValueFactory<Seance,Date>("Date"));
        Duree.setCellValueFactory(new PropertyValueFactory<Seance,String>("Duree"));
        Etat.setCellValueFactory(new PropertyValueFactory<Seance,String>("Etat"));
        Callback<TableColumn<Seance,String>,TableCell<Seance,String>> cellFactory=(TableColumn<Seance, String> param)->{
            final TableCell<Seance,String> cell=new TableCell<Seance,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                else{
                        final Button editButton=new Button("Modifier");
                        editButton.setOnAction(event ->{
                        Seance s=getTableView().getItems().get(getIndex());
                        Seance s1=ss.read(s.getId());
                        FXMLLoader loder = new FXMLLoader(getClass().
                              getResource("DemandeModification.fxml"));
                try {
                    Parent root = loder.load();
                    DemandeModificationController dc = loder.getController();
                    dc.setDate(new DatePicker(s1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
                    dc.setDuree(new TextField(s1.getDuree()));
                    dc.setMessage(new TextField(s1.getMessage()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                            /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You have clicked \n"+s.getMessage());
                            alert.show();*/
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        Modifier.setCellFactory(cellFactory);
        Callback<TableColumn<Seance,String>,TableCell<Seance,String>> cellFactory2=(TableColumn<Seance, String> param)->{
            final TableCell<Seance,String> cell=new TableCell<Seance,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                else{
                        final Button editButton=new Button("Annuler");
                        editButton.setStyle("-fx-background-color: red;");
                        editButton.setOnAction(event ->{
                            Seance s=getTableView().getItems().get(getIndex());
                            ss.supprimer(s);
                            /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You have clicked \n"+s.getMessage());
                            alert.show();*/
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        Annuler.setCellFactory(cellFactory2);
        SeanceTable.setItems(SeanceList);
        System.out.println(SeanceList);    }    

    @FXML
    private void RedrRes(ActionEvent event) {
                try {
            root = FXMLLoader.load(getClass().getResource("ReserverSeance.fxml"));
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
                try {
            root = FXMLLoader.load(getClass().getResource("SuiviSeance.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());}
    }
    
}
