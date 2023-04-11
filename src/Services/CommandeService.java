/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Chaima
 */
public class CommandeService {
    
    
     public void add(Commande c) throws SQLException {
        //request 
        String req="INSERT INTO `commande`(`user_id_id`, `code_id`, `date`,`etat`) VALUES (?,?,?,?)";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,c.getCreatedBy());
            pst.setInt(2,c.getCode_id());
            pst.setDate(3,c.getDate());
            pst.setString(4,c.getEtat());          
            pst.executeUpdate();
            System.out.println("commande ajouter avec Succes");
            
              ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
           c.setId(rs.getInt(1));
        }
    }

   
    public void update(Commande c, int idC) throws SQLException {
        //request 
        String req="UPDATE `commande` SET  `etat`=? ,`code_id`=? WHERE id =?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setString(1,c.getEtat());
        pst.setInt(2,c.getCode_id());
        pst.setInt(3,idC);
        pst.executeUpdate();

        System.out.println("modification terminée avec Succes");

         
    }

   
    public void delete(int idC) throws SQLException {
        //request 
        String req="DELETE FROM commande WHERE id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("Commande Supprimer avec Succes");     
    }
    
       public void Livred(int idC) throws SQLException {
        //request 
        String req="UPDATE commande SET etat='livred' WHERE id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Succes");     
    }

              public void Annuler(int idC) throws SQLException {
        //request 
        String req="UPDATE commande SET etat='ANNULER' WHERE id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Succes");     
    }
    
    
   
    public List show() throws SQLException {
        //LIST
        List<Commande> commandes = new ArrayList<>();
        //request 
        String req ="SELECT * FROM commande";
            //insert
            Statement st =  new MyConnection().cn.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                commandes.add(new Commande(rs.getInt("id"),rs.getInt("user_id_id"), rs.getInt("code_id"), rs.getDate("date"), rs.getString("etat")));
            }

        return commandes; 
    }
    
        public ObservableList<Commande> ShowC() {
        ObservableList<Commande> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM commande ";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();  
            while (rs.next()) {
                list.add(new Commande(rs.getInt("id"),rs.getInt("user_id_id"), rs.getInt("code_id"), rs.getDate("date"), rs.getString("etat")));
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return list;
    }
        public Commande findById(int idc) {
        Commande c = null;
        try {
            String req = "select * FROM commande where id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c = new Commande(rs.getInt("id"),rs.getInt("user_id_id"), rs.getInt("code_id"), rs.getDate("date"), rs.getString("etat"));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return c;
    }
        
                public List findByIdU(int idc) {
         List<Commande> commandes = new ArrayList<>();
        try {
            String req = "select * FROM commande where user_id_id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                commandes.add(new Commande(rs.getInt("id"),rs.getInt("user_id_id"), rs.getInt("code_id"), rs.getDate("date"), rs.getString("etat")));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return commandes;
    }
    
    
}
