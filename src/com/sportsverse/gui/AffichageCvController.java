/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Achref
 */
public class AffichageCvController implements Initializable {

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
    private Button ListCoach;
    @FXML
    private TextField txt_certif;
    @FXML
    private TextField txt_desc;
    private ComboBox<?> NExp;
    @FXML
    private Spinner<?> sp_tarif;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_nom;
    @FXML
    private Spinner<?> sp_duree;
    private ComboBox<?> LActivites;
    @FXML
    private TextField txt_prenom1;
    @FXML
    private TextArea list_cv;

    public TextArea getList_cv() {
        return list_cv;
    }

    public void setList_cv(String list_cv) {
        this.list_cv.setText(list_cv);
    }

    public TextField getTxt_certif() {
        return txt_certif;
    }

    public void setTxt_certif(String txt_certif) {
        this.txt_certif.setText(txt_certif);
    }

    public TextField getTxt_desc() {
        return txt_desc;
    }

    public void setTxt_desc(String txt_desc) {
        
        this.txt_desc.setText(txt_desc);
    }

    public ComboBox<?> getNExp() {
        return NExp;
    }

    public void setNExp(String NExp) {
        this.NExp.getValue();
    }

    public Spinner<?> getSp_tarif() {
        return sp_tarif;
    }

    public void setSp_tarif(int sp_tarif) {
        this.sp_tarif.getValue();
    }

    public TextField getTxt_prenom() {
        return txt_prenom;
    }

    public void setTxt_prenom(String txt_prenom) {
        this.txt_prenom.setText(txt_prenom);
    }

    public TextField getTxt_nom() {
        return txt_nom;
    }

    public void setTxt_nom(String txt_nom) {
        this.txt_nom.setText(txt_nom);
    }

    public Spinner<?> getSp_duree() {
        return sp_duree;
    }

    public void setSp_duree(int sp_duree) {
        this.sp_duree.getValue();
    }

    public ComboBox<?> getLActivites() {
        return LActivites;
    }

    public void setLActivites(ComboBox<?> LActivites) {
        this.LActivites = LActivites;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void RedirReserver(ActionEvent event) {
    }

    @FXML
    private void RedirSuivi(ActionEvent event) {
    }

    @FXML
    private void RedirCoach(ActionEvent event) {
    }

    
}
