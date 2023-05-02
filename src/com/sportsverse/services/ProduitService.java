/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;


import com.sportsverse.entities.Categorie;
import com.sportsverse.entities.Produit;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Fatma
 */
public class ProduitService {

        private Connection cnx = MaConnection.getInstance().getCnx();
   CategorieService c=new CategorieService();
        Statement ste;
         public final String SELECT_CATEGORIE_BY_ID = "SELECT * FROM Categorie WHERE id = ? LIMIT 1";
        private final String SELECT_PRODUIT_BY_CATEGORIE="SELECT * FROM Produit WHERE categorie_id=? ";
    private final String SELECT_PRODUIT_BY_Nom="SELECT * FROM Produit WHERE nom_produit = ?";
        public void ajouter(Produit p) {
            String req = "INSERT INTO Produit(nom_produit  ,prix_ttc ,categorie_id, image ,quantite  ) VALUES( ? ,? ,? ,? ,?  );";
            try {
              
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, p.getNom_produit());
                pst.setDouble(2, p.getPrix_ttc());
                pst.setInt(3, p.getC().getId());
                pst.setString(4, p.getImage());
               
                pst.setInt(5, p.getQuantite());
                pst.executeUpdate();
                System.out.println("produit ajoutée !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

       
        public void modifier(Produit p) {
            String req = "UPDATE  Produit SET nom_produit=?  ,prix_ttc=? ,categorie_id=?, image=? ,quantite=? ,  WHERE id_Produit=?";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
               pst.setString(1, p.getNom_produit());
                pst.setDouble(2, p.getPrix_ttc());
             
                pst.setString(3, p.getImage());
               
                pst.setInt(6, p.getQuantite());
                pst.executeUpdate();
                System.out.println("produit modifié !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        
        public void supprimer(Produit p) {
            String req = "DELETE FROM produit WHERE id_Produit=?";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setInt(1, p.getId());
                pst.executeUpdate();
                System.out.println("produit supprimé !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

       
        public List<Produit> afficher() {
            List<Produit> produits = new ArrayList<>();

            String req = "SELECT * FROM Produit";
            try {
                PreparedStatement pst = cnx.prepareStatement(req);
                ResultSet result = pst.executeQuery(req);
                while(result.next()) {
                   Produit p=new Produit(result.getInt(1), result.getInt("quantite"), c.read(result.getInt("categorie_id")), result.getString("nom_produit"), result.getString("image"), result.getDouble("prix_ttc"));
                   produits.add(p);
                }
                
                System.out.println("produit récupérées !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return produits;
        }
        public Categorie getCategorieById(int id) {
        
        Categorie categorie = new Categorie();
        
        try{
            ste = cnx.createStatement();
            PreparedStatement stmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("nom"));
            }
            
        }catch(SQLException sQLException){
            System.out.println(sQLException.getMessage());
        }catch(IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
        }catch(NullPointerException nullPointerException){
            System.out.println(nullPointerException.getMessage());
        }
        
        return categorie;
    }
        public List<Produit> getProduitByCategorie(int id) {
        
        List<Produit> prod = new ArrayList<Produit>();
        
        try{
           ste = cnx.createStatement();
           
                
            PreparedStatement stmt = cnx.prepareStatement(SELECT_PRODUIT_BY_CATEGORIE);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            
          while (result.next()) {
            Produit resultProduit = new Produit(
                    result.getInt(1), result.getInt("quantite"), getCategorieById(result.getInt("categorie_id")), result.getString("nom_produit"), result.getString("image"), result.getDouble("prix_ttc")
                   );
                     
                    
            prod.add(resultProduit);
        }
        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
         public List<Produit> getProduitByTitre(String nom_produit) {
        
        List<Produit> prod = new ArrayList<Produit>();
        
        try{
            ste = cnx.createStatement();
            PreparedStatement stmt = cnx.prepareStatement(SELECT_PRODUIT_BY_Nom);
            stmt.setString(1, nom_produit);
            ResultSet result = stmt.executeQuery();
            
          while (result.next()) {
            Produit resultProduit = new Produit(
                     result.getInt(1), result.getInt("quantite"), getCategorieById(result.getInt("categorie_id")), result.getString("nom_produit"), result.getString("image"), result.getDouble("prix_ttc")
                   );
                   
            prod.add(resultProduit);
        }
        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
    }
    }
