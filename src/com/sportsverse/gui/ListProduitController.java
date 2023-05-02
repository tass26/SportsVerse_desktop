/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Categorie;
import com.sportsverse.entities.Produit;
import com.sportsverse.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListProduitController implements Initializable {

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
    private HBox ProduitList;
    @FXML
    private TableView<Produit> xtable;
    @FXML
    private TableColumn<Produit, String> xnom;
    @FXML
    private TableColumn<Produit, String> xcategorie;
    @FXML
    private TableColumn<Produit, Integer> xquantite;
    @FXML
    private TableColumn<Produit,Double> xprix;
    @FXML
    private TableColumn<Produit, String> ximage;
    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mcategorie;
    @FXML
    private TextField Mquantite;
    @FXML
    private TextField Mprix;
    @FXML
    private TextField Mimage;
    @FXML
    private TextField filtrerField;
     @FXML
    private ComboBox<String> StatV;
 ProduitService ps=new ProduitService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xnom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_produit()));
        xcategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getC().getNom()));
        xquantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
        ximage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage()));
        xprix.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrix_ttc()).asObject());
        ProduitService crud = new ProduitService();
        // Populate the table with data
        List<Produit> data;

            data = crud.afficher();
            xtable.getItems().setAll(data);
             xtable.setRowFactory(tv -> {
		     TableRow<Produit> myRow = new TableRow<>();
		     myRow.setOnMouseClicked ((event) -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            int myIndex =  xtable.getSelectionModel().getSelectedIndex();
                            Mnom.setText(xtable.getItems().get(myIndex).getNom_produit());
		           //double Mprix = Double.parseDouble(String.valueOf(xtable.getItems().get(myIndex).getPrix_ttc()));
		            
                         //int Mquantite = Integer.parseInt(String.valueOf(xtable.getItems().get(myIndex).getQuantite()));
                         Mquantite.setText(String.valueOf((Integer)xtable.getItems().get(myIndex).getQuantite()));
                           Mprix.setText(String.valueOf((Double)xtable.getItems().get(myIndex).getPrix_ttc()));
                            Mimage.setText(xtable.getItems().get(myIndex).getImage());
                            Mcategorie.setText(xtable.getItems().get(myIndex).getC().getNom());
                           
                         
                           
		        }
		     });
		        return myRow;
                   });
        
    }    

   


    @FXML
    private void supprimer(ActionEvent event) {
         ProduitService crud = new ProduitService();
    Produit produit = xtable.getSelectionModel().getSelectedItem();

    if (produit != null) {
        int id = produit.getId();
        crud.supprimer(produit);
        xtable.getItems().remove(produit);
    }
    }

    @FXML
    private void  modifier_produit(ActionEvent event) throws SQLException {
       
    }

    @FXML
    private void filtrer(KeyEvent event) {
         ObservableList<Produit> filteredProduits = FXCollections.observableArrayList(ps.afficher());
          

        ObservableList<Produit> newdata = filteredProduits.stream()
                .filter(n -> n.getNom_produit().toLowerCase().contains(filtrerField.getText().toLowerCase())
               
                || n.getNom_produit().toLowerCase().contains(filtrerField.getText().toLowerCase())
                || n.getNom_produit().toLowerCase().equals(filtrerField.getText()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        xtable.setItems(newdata);
    }
    @FXML
    private void ToPdf(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root=this.xtable;   
              job.printPage(root);
              
              job.endJob(); 
        
    }
    }
    @FXML
    private void StatV(ActionEvent event) {
        String S = (String) StatV.getValue();

        if (S == "Stat Prix") {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/Gui/VoyageStatPrix.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (S == "Stat Destination") {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/Gui/VoyageStat.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (S == "Stat Valabilite") {
            try {

                Parent parent = FXMLLoader.load(getClass().getResource("/Gui/VoyageStatValabilite.fxml"));
                Scene scene = new Scene(parent);

                Stage stage = new Stage();
                //stage.getIcons().add(new Image("/images/logo.png"));
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    }
    

