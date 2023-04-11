/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Codepromo;
import Entities.Commande;
import Tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chaima
 */
public class CodepromoService {
         public void add(Codepromo c) throws SQLException {
        //request 
        String req="INSERT INTO `codepromo`(`code`, `created_at`, `valeur`) VALUES (?,?,?)";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setString(1,c.getCode());
            pst.setDate(2,c.getCreated_At());
            pst.setInt(3,c.getValeur());          
            pst.executeUpdate();
            System.out.println("Codepromo ajouter avec Succes");
    }

   
    public void update(Codepromo c, int idC) throws SQLException {
        //request 
        String req="UPDATE `codepromo` SET  `code`=? ,`valeur`=? WHERE id =?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setString(1,c.getCode());
        pst.setInt(2,c.getValeur());
        pst.setInt(3,idC);
        pst.executeUpdate();

        System.out.println("modification terminée avec Succes");

         
    }

   
    public void delete(int idC) throws SQLException {
        //request 
        String req="DELETE FROM `codepromo` WHERE id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("Codepromo Supprimer avec Succes");     
    }

    
    
   
    public List show() throws SQLException {
        //LIST
        List<Codepromo> codepromos = new ArrayList<>();
        //request 
        String req ="SELECT * FROM codepromo";
            //insert
            Statement st =  new MyConnection().cn.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                codepromos.add( new Codepromo(rs.getString("code"),  rs.getDate("created_at"),rs.getInt("valeur")));
                      
            }

        return codepromos; 
    }
    
        
    public Codepromo findByCode(String idc) {
        Codepromo cp = null;
        try {
            String req = "select * FROM codepromo where code=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setString(1, idc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cp = new Codepromo(rs.getString("code"), rs.getDate("created_at"), rs.getInt("valeur"));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return cp;
    }
        public Codepromo findById(int idcp) {
        Codepromo cp = null;
        try {
            String req = "select * FROM codepromo where id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idcp);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cp = new Codepromo(rs.getString("code"), rs.getDate("created_at"), rs.getInt("valeur"));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return cp;
    }
                public ObservableList<Codepromo> ShowC() {
        ObservableList<Codepromo> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM codepromo ";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                list.add(new Codepromo(rs.getInt("id"),rs.getString("code"), rs.getDate("created_at"), rs.getInt("valeur")));
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return list;
    }
        
        
    
}
