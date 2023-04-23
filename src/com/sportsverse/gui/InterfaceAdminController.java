/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.service.UserService;
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
        // TODO
    }   
    @FXML
  private void ListUser(ActionEvent event) throws SQLException, IOException {
        UserService cr = new UserService();
//      cr.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUser.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                System.out.println(cr.getCurrentUser());
                
    }

    @FXML
    private void filter(KeyEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    
}
