/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.tools.MaConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProduitStatPrixController implements Initializable {

    @FXML
    private ImageView ImageP;
    @FXML
    private PieChart Produit_stat;

    /**
     * Initializes the controller class.
     */
   private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        cnx = MaConnection.getInstance().getCnx();
       stat();
    }        
    private void stat() {
          try{
           
           String query ="select SUM(prix_ttc),`nom_produit` from Produit GROUP BY `prix_ttc`;";
           

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("nom_produit"),rs.getInt("SUM(prix_ttc)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        
         Produit_stat.setTitle("**Statistiques des Prix Par nom**");
        Produit_stat.setLegendSide(Side.LEFT);
       Produit_stat.setData(data);
    }
}
