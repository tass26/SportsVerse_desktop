/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed Ali
 */
public class InterfaceMain extends Application {
    
     @Override
    public void start(Stage primaryStage) {
  
       Parent root; 
      try{ 
       
        root=FXMLLoader.load(getClass().getResource("interface_inscription.fxml"));
      // root=FXMLLoader.load(getClass().getResource("ListUser.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Interface Inscription");
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);

        primaryStage.show();
    }
    catch(IOException e){System.out.print(e.getMessage());}
    
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
