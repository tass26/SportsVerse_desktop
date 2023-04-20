/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.service;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class UserService {
    Statement ste;
    Connection cnx;
    EmplacementService ps = new EmplacementService();
    private final String Select_User="SELECT * FROM `user`";
    public UserService() {
        cnx = MaConnection.getInstance().getCnx();
    }
        public User read(int id){
        String sql = "SELECT is_verified, is_banned, nom, prenom, adresse, num_tel, email,role,password FROM User WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new User(id,
                            result.getBoolean("is_verified"),
                            result.getString("is_banned"),
                            result.getString("nom"),
                            result.getString("prenom"),
                            result.getString("adresse"),
                            result.getString("num_tel"),
                            result.getString("email"),
                            result.getString("role"),
                            result.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;
    }
    public void ajouterUser(User p) {
        try {
            String req = "INSERT INTO `user` (`nom`, `prenom`,`adresse`,`num_tel`,`email`,`roles`,`password`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getAdresse());
            ps.setString(4, p.getNum_tel());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getRole());
            ps.setString(7, hashPassword(p.getPassword()));
            
           
            ps.executeUpdate();
            System.out.println("Ajouter avec succée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
     public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
     public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void modifierUser(int id, String nom,String prenom,String adresse,String num_tel ,String email) {
        try {
            String req = "UPDATE `user` SET `nom` = '" + nom + "', `prenom` = '" + prenom +  "',`adresse` = '" + adresse +  "',`email` = '" + email +  "', `num_tel` = '" + num_tel + "' WHERE `user`.`id` = " +id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
      public List<User> afficherUsers() {
    List<User> prod = new ArrayList<User>();
        try {
        ste = cnx.createStatement();
        ResultSet result = ste.executeQuery(Select_User);
        
        while (result.next()) {
            User resultProduit = new User(
                    result.getInt("id"),
                    result.getString("nom"),
                    result.getString("prenom"),
                    result.getString("adresse"),
                    result.getString("num_tel"),
                    result.getString("email"),
                  
                    result.getString("roles"));
            prod.add(resultProduit);
        }
        System.out.println(prod);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return prod;
 }
    public List<User> getCoachs() {
        List<User> users = new ArrayList<>();
        String sql="select * from user";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("roles").substring(2, 12).equals("ROLE_COACH")){
                    User u = new User(
                            rs.getInt("is_verified"),
                            rs.getInt("is_banned"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("adresse"),
                            rs.getString("num_tel"),
                            rs.getString("email"),
                            rs.getString("roles"),
                            rs.getString("password"));                
                    users.add(u);}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return users;
    }
}
