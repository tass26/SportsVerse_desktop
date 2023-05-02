/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;


import com.sportsverse.entities.Categorie;
import com.sportsverse.entities.Produit;
import com.sportsverse.resources.Util;
import com.sportsverse.services.CategorieService;
import com.sportsverse.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterProduitController implements Initializable {

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
    private Button btn_Ajouter;
    @FXML
    private TextField NomP;
    @FXML
    private ComboBox<Categorie> ListeCategorie;
    @FXML
    private TextField Image;
    @FXML
    private TextField Quantite;
    @FXML
    private Button ProductImg;
    @FXML
    private TextField Prix;
    CategorieService cs =new CategorieService();
     private Stage stage;
    private Scene scene;
    private Parent root;
     String res,image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          List<Categorie> categories = cs.DisplayCat();
        Categorie[] categorieChoix = new Categorie[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categorieChoix[i] = categories.get(i);
        }
        ListeCategorie.getItems().addAll(categorieChoix);
    }
       

    @FXML
    private void RedirAccueil(ActionEvent event) {
    }

    @FXML
    private void Login(ActionEvent event) {
    }

    @FXML
    private void RedirProduit(ActionEvent event) {
    }

    @FXML
    private void RedirPanier(ActionEvent event) {
    }

    @FXML
    private void RedirReserverS(ActionEvent event) {
    }

    @FXML
    private void RedirSuiviS(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
         EventHandler<MouseEvent> Cfile = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Util u=new Util();
                res = u.ImgPicker();
            }
        };
        ProductImg.setOnMouseClicked(Cfile);
       image = res;
        int quantite =Integer.parseInt(Quantite.getText());
       int prix=Integer.parseInt(Prix.getText());
        
                String nom=NomP.getText();
                
                 
                  Categorie categorie = (Categorie) ListeCategorie.getValue();
        Produit p=new Produit(quantite,categorie, nom, image, prix);
        ProduitService pp=new ProduitService();
        pp.ajouter(p);
       
        FXMLLoader loder = new FXMLLoader(getClass().
                getResource("ListProduit.fxml"));
        try {
             root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
       
        
    }
     
    @FXML
    private void AddImage(ActionEvent event) {
        Util u = new Util();
        res = u.ImgPicker();
    }
    
}
