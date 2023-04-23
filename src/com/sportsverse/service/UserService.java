/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.service;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.sql.Array;
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
    Connection cnx;
    EmplacementService ps = new EmplacementService();

    public UserService() {
        cnx = MaConnection.getInstance().getCnx();
    }
        public User read(int id){
        String sql = "SELECT is_verified, is_banned, nom, prenom, adresse, num_tel, email, password FROM User WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new User(id,
                            result.getInt("is_verified"),
                            result.getInt("is_banned"),
                            result.getString("nom"),
                            result.getString("prenom"),
                            result.getString("adresse"),
                            result.getString("num_tel"),
                            result.getString("email"),
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