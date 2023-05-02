/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Ali
 */
public class InterfaceAdminController implements Initializable {

    @FXML
    private TextField filterField;
    private Button gotoEchangeBtn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gotoEchangeBtn.setOnAction((event) -> {
            gotoEchange(new Stage());
        });
    }   
  private void ListUser(ActionEvent event) throws SQLException, IOException {
        UserService cr = new UserService();
//      cr.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inter_adm_list.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                System.out.println(cr.getCurrentUser());
                
    }
  public void gotoEchange(Stage primaryStage){
       
      Parent Echangeroot;
        try {
           
            Echangeroot = FXMLLoader.load(getClass().getResource("inter_adm_list.fxml"));
            Scene scene = new Scene(Echangeroot, java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                                        java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());

            primaryStage.setTitle("Reshopi");
            primaryStage.setScene(scene);
//            primaryStage.setFullScreen(true);
            primaryStage.setResizable(false);
            primaryStage.show();
           
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }



    
}
