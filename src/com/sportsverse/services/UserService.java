/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 *
 * @author user
 */
public class UserService {
    Statement ste;
    Connection cnx;
         private static User currentUser;

    EmplacementService ps = new EmplacementService();
    private final String Select_User="SELECT * FROM `user`";
    public UserService() {
        cnx = MaConnection.getInstance().getCnx();
    }
        public User read(int id){
        String sql = "SELECT is_verified, is_banned, nom, prenom, adresse, num_tel, email,roles,password FROM User WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new User(id,
                            result.getBoolean("is_verified"),
                            result.getString("nom"),
                            result.getString("prenom"),
                            result.getString("adresse"),
                            result.getString("num_tel"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("roles"),
                            result.getString("is_banned"));
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
//     public static String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashInBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
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
    List<User> users = new ArrayList<User>();
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
            users.add(resultProduit);
        }
        System.out.println(users);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return users;
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
    public static boolean signIn(String email, String password) throws SQLException {
      Connection connection = MaConnection.getInstance().getCnx();
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");

          // Set the email parameter
          statement.setString(1, email);

          // Execute the query and retrieve the user information
          ResultSet resultSet = statement.executeQuery();
              if (!resultSet.next()) {
                  // No record found for the email
                  return false;
              }

              // Retrieve the stored hashed password and salt
              String storedHashedPassword = resultSet.getString("password");
             

              // Hash the provided password using the same n
              String hashedPassword = hashPassword(password);

              // Compare the two hashed passwords for equality
              if (!storedHashedPassword.equals(hashedPassword)) {
                  System.out.println("incorrect mot de passe");
                  return false;
              }

              // Create a User object with the retrieved information
              User user = new User(
                      resultSet.getInt("id"),
                      resultSet.getString("nom"),
                      resultSet.getString("prenom"),
                      resultSet.getString("adresse"),
                      resultSet.getString("num_tel"),
                      resultSet.getString("email"),
                      resultSet.getString("roles"),
                     resultSet.getString("is_banned")
              );

              // Store the user as the current user
              currentUser = user;
              System.out.println("vous etes connectée");
              System.out.println(currentUser);
               
              return true;
          }

      
  
        
        public static User getCurrentUser() {
          return currentUser;
        }
        public static boolean changePassword(String email, String oldPassword, String newPassword) {
         try (Connection connection = MaConnection.getInstance().getCnx();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?")) {

        // Set the email parameter
        statement.setString(1, email);

        // Execute the query and retrieve the user information
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                // No record found for the email
                return false;
            }

            // Retrieve the stored hashed password
            String storedHashedPassword = resultSet.getString("password");

            // Hash the provided old password using the same hash function
            String hashedOldPassword = hashPassword(oldPassword);

            // Compare the two hashed passwords for equality
            if (!storedHashedPassword.equals(hashedOldPassword)) {
                // Incorrect old password
                return false;
            }

            // Hash the new password using the same hash function
            String hashedNewPassword = hashPassword(newPassword);

            // Update the user's password in the database
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE email = ?")) {
                updateStatement.setString(1, hashedNewPassword);
                updateStatement.setString(2, email);
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated == 1) {
                    return true;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
        public void blockage(int p) {
        try {
           
    
            String req = "UPDATE user SET is_banned = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, "bloque");
            ps.setInt(2, p);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        public void deblockage(int id) 
        
        {
        try {
           
    
           String req = "UPDATE user SET is_banned = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, "non_bloqué");
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
     public boolean checkuser(String usernameouemail) {
    return afficherUsers().stream()
        .anyMatch(u -> u.getEmail().equals(usernameouemail));
}
     public User getUserByEmail(String email){
        return afficherUsers().stream().filter(
                u->u.getEmail().equals(email)).findFirst().orElse(null);
    }
        public User getUserByAdress(String ad){
        String sql = "SELECT nom, prenom, adresse, num_tel, email, roles FROM User WHERE email = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, ad);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getString("nom"),
                            result.getString("prenom"),
                            result.getString("adresse"),
                            result.getString("num_tel"),
                            result.getString("email"),
                            result.getString("roles"));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;
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
                    User u = new User(rs.getInt("id"),
                            rs.getBoolean("is_verified"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("adresse"),
                            rs.getString("num_tel"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("roles"),
                            rs.getString("is_banned"));                
                    users.add(u);}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return users;
    }
    public void modifierO(User o,int id) throws SQLException {
         
    }
      public void supprimer(int id) {
   
      
      
        }
      
      public User getIduserByIdRec(int id){
        User u =new User();
        try {
            String query="SELECT * FROM `reclamation` where id="+id;
            Statement st=cnx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setId(res.getInt("user_id_id"));
               
                
            }
        }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return u;
        
        }
    
    public User getuserbyiduser(int id){
        User u =new User();
        try {
            String query="SELECT * FROM `User` where id="+id;
            Statement st=cnx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setEmail(res.getString("email"));
                
               
                
            }
        }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return u;
        
        }

    public void ajouterR(User r) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
