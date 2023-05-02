/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Reclamation;
import com.sportsverse.entities.Reponse;
import com.sportsverse.entities.User;
//import com.sportsverse.entities.User;
import com.sportsverse.services.ReclamationService;
import com.sportsverse.services.ReponseService;
import com.sportsverse.services.UserService;
import com.sportsverse.tools.MaConnection;
import com.sportsverse.tools.MailAPI;
import java.io.IOException;
//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
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
    ReclamationService rs=new ReclamationService();
    ReponseService rps=new ReponseService();
    UserService us=new UserService();
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    @FXML
    private TextField tfreponse;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfsujet;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfrecherche;
        private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
  public void initialize(URL url, ResourceBundle rb) {
        try {
            TableColumn cbuttonreponse = new TableColumn("Action");
            refreshList();
            recherche_avance();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AffichageRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public void refreshList() throws SQLException{
        data.clear();
        data=FXCollections.observableArrayList(rs.afficheListeR());
        csujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        cetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tvreclamation.setItems(data);
    }
    
    @FXML
    private void modifier(ActionEvent event) throws SQLException, MessagingException {
        if(tvreclamation.getSelectionModel().getSelectedItem()!=null){
            Reclamation r=new Reclamation();
            Reponse rp= new Reponse();
            int id=tvreclamation.getSelectionModel().getSelectedItem().getId();
            System.out.println(tvreclamation.getSelectionModel().getSelectedItem());
    
            r.setId(tvreclamation.getSelectionModel().getSelectedItem().getId());
            r.setid_user(tvreclamation.getSelectionModel().getSelectedItem().getid_user());
            r.setSujet(tfsujet.getText());
            r.setNom_client(tfnom.getText());
           
            r.setDescription(tfdescription.getText());
            rp.setDescription(tfreponse.getText());
            
            r.setEtat("traite");
            System.out.println(r);
            rs.modifierO(r,id);//id reclamation
            rps.ajouterR(rp,id);
            
            User u = new User();
            
               User u1=us.getIduserByIdRec(id);
                System.out.println(u1.getId());
               User u2=us.getuserbyiduser(u1.getId());
               System.out.println(u2.getEmail());
              
               
               
               
               
               
               
               
            
            
        
                
            
            MailAPI.sendMail(u2.getEmail(),"Reponse sur votre reclamation", "Apres avoir revisé votre reclamation on a constate:"+tfreponse.getText());
            refreshList();
        }
    }
    
    

    public void recherche_avance() throws SQLException{
        //remplire lobservablelist
        data.clear();
        data.addAll(rs.afficheListeR());
        //liste filtrer
        FilteredList<Reclamation> filtreddata;
        filtreddata = new FilteredList<>(data,r->true);
        //creation du listenere a partire du textfield
        tfrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filtreddata.setPredicate(Reclamation->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
              
                if(Reclamation.getEtat().indexOf(newValue)!=-1){
                    return true;
                }
                else if(Reclamation.getSujet().indexOf(newValue)!=-1){
                    return true;
                }
                else if(Reclamation.getDescription().indexOf(newValue)!=-1){
                    return true;
                }
                else if(Reclamation.getNom_client().indexOf(newValue)!=-1){
                    return true;
                }
                
                
                else{
                    return false;
                }
            });
            tvreclamation.setItems(filtreddata);
        });
    }
    

    @FXML
    private void supprimer(javafx.event.ActionEvent event) throws SQLException {
        if(tvreclamation.getSelectionModel().getSelectedItem()!=null){
            int id=tvreclamation.getSelectionModel().getSelectedItem().getId();
            rs.supprimer(id);
            refreshList();
        }

    }
    
    @FXML
    private void fillforum(MouseEvent event) {
        Reclamation r=tvreclamation.getSelectionModel().getSelectedItem();
        tfnom.setText(r.getNom_client());
        tfsujet.setText(r.getSujet());
        tfdescription.setText(r.getDescription());
        
    }
        

    @FXML
    private void select(MouseEvent event) {
    }

    @FXML
    private void onstats(ActionEvent event) throws IOException {
    
FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsRec.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root);
Stage stage = new Stage();
stage.setTitle("Stats");
stage.setScene(scene);
stage.show();
    }

    @FXML
    private void backToAccueil(MouseEvent event) {
                        try {
            root = FXMLLoader.load(getClass().getResource("inter_adm_list.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    }
}

    
