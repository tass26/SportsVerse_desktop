/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class ProductService {

    public void add(Product c) throws SQLException {
        //request 
        String req = "INSERT INTO `Product`(`categorie_id`, `nom_produit`, `prix_ttc`,`quantite`,`image`) VALUES (?,?,?,?,?)";

        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setInt(1, c.getCategorie_id());
        pst.setString(2, c.getNom_produit());
        pst.setDouble(3, c.getPrix_ttc());
        pst.setInt(4, c.getQuantite());
        pst.setString(5, c.getImage());

        pst.executeUpdate();
        System.out.println("Produit ajouter avec Succes");
    }

    public void update(Product c, int idC) throws SQLException {
        //request 
        String req = "UPDATE `produit` SET  `quantite`=?  WHERE id =?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setInt(2, c.getQuantite());
        pst.setInt(3, idC);
        pst.executeUpdate();

        System.out.println("modification terminée avec Succes");

    }

    public void delete(int idC) throws SQLException {
        //request 
        String req = "DELETE FROM `produit` WHERE id=?";

        PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
        pst.setInt(1, idC);
        pst.executeUpdate();
        System.out.println("produit Supprimer avec Succes");
    }

    public List show() throws SQLException {
        //LIST
        List<Product> products = new ArrayList<>();
        //request 
        String req = "SELECT * FROM produit";
        //insert
        Statement st = new MyConnection().cn.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            products.add(new Product(rs.getInt("id"),rs.getInt("categorie_id"), rs.getString("nom_produit"), rs.getDouble("prix_ttc"), rs.getInt("quantite"), rs.getString("image")));
        }

        return products;
    }
    
    
    public Product findById(int idp) {
        Product p = null;
        try {
            String req = "select * FROM produit where id=? ";
             PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idp);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getInt("id"),rs.getInt("categorie_id"), rs.getString("nom_produit"), rs.getDouble("prix_ttc"), rs.getInt("quantite"), rs.getString("image"));
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return p;
    }

}
