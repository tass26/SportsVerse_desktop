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
public class ProduitStatController implements Initializable {

    @FXML
    private ImageView ImageP;
    @FXML
    private PieChart produit_stat;
private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
int n;
    /**
     * Initializes the controller class.
     */
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MaConnection.getInstance().getCnx();
            stat();
    }    

    private void stat() {
          try{
           
           String query ="select COUNT(*),`prix_ttc`  from Produit GROUP BY `prix_ttc`;";
          

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("prix_ttc"),rs.getInt("COUNT(*)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
         produit_stat.setTitle("**Statistiques Des produits courant **");
        produit_stat.setLegendSide(Side.LEFT);
        produit_stat.setData(data);
    }
 
    
}
