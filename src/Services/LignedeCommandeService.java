/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LignedeCommande;
import Entities.Product;
import Tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chaima
 */
public class LignedeCommandeService {
     public void add(LignedeCommande c) throws SQLException {
        //request 
        String req="INSERT INTO `ligne_de_commande`(`id_produit_id`, `id_commande_id`, `quantite`) VALUES (?,?,?)";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,c.getIdP());
            pst.setInt(2,c.getIdC());
            pst.setInt(3,c.getQte());          
            pst.executeUpdate();
            System.out.println("Ligne de commande ajouter avec Succes");
    }

   
    public void update(LignedeCommande c, int idC) throws SQLException {
        //request 
        String req="UPDATE `ligne_de_commande` SET  `quantite`=?  WHERE id =?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setInt(2,c.getQte());
        pst.setInt(3,idC);
        pst.executeUpdate();

        System.out.println("modification terminée avec Succes");

         
    }

   
    public void delete(int idC) throws SQLException {
        //request 
        String req="DELETE FROM `ligne_de_commande` WHERE id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("ligne_de_commande Supprimer avec Succes");     
    }
    
       public void deleteByIdC(int idC) throws SQLException {
        //request 
        String req="DELETE FROM `ligne_de_commande` WHERE id_commande_id=?";

        
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1,idC);            
            pst.executeUpdate();
            System.out.println("ligne_de_commande Supprimer avec Succes");     
    }

    
    
   
    public List show() throws SQLException {
        //LIST
        List<LignedeCommande> lignedeCommandes = new ArrayList<>();
        //request 
        String req ="SELECT * FROM ligne_de_commande";
            //insert
            Statement st =  new MyConnection().cn.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                lignedeCommandes.add(new LignedeCommande(rs.getInt("id_produit_id"), rs.getInt("id_commande_id"), rs.getInt("quantite")));
            }

        return lignedeCommandes; 
    }
    
            public List findByIdC(int idc) throws SQLException {
        List<LignedeCommande> ldcs = new ArrayList<>();
        try {
            String req = "select * FROM ligne_de_commande where id_commande_id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idc);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ldcs.add(new LignedeCommande(rs.getInt("id_produit_id"), rs.getInt("id_commande_id"), rs.getInt("quantite")));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return ldcs;
    }
    
            
            public Integer findQteByproductid(int id) throws SQLException {
        int qte = 0;
        try {
            String req = "select quantite FROM ligne_de_commande where id_produit_id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               qte = rs.getInt("quantite");
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return qte;
    }
//          public void IncrementByProductId(int i) throws SQLException {
//
//            String req = "UPDATE ligne_de_commande SET quantite = quantite + 1 where id_produit_id=? ";
//             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
//            pst.setInt(1, i);
//             pst.executeUpdate();
//            System.out.println("++");   
//
//    } 
//                public void DecrementByProductId(int i) throws SQLException {
//            String req = "UPDATE ligne_de_commande SET quantite = quantite - 1 where id_produit_id=? ";
//             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
//            pst.setInt(1, i);
//             pst.executeUpdate();
//            System.out.println("--");   
//
//    }  
}
