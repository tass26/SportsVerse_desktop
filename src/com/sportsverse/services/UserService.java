/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;


import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
