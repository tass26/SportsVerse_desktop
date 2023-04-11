/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.services.*;
import com.sportsverse.entities.Emplacement;
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
 * @author user
 */
public class EmplacementService implements NewInterface<Emplacement>{
    Connection cnx;

    public EmplacementService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Emplacement t) {
        String sql="insert into Emplacement(governorat,delegation, type, adresse, localite) values(?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getGovernorat());
            ste.setString(2, t.getDelegation());
            ste.setString(3, t.getType());
            ste.setString(4, t.getAdresse());
            ste.setString(5, t.getLocalite());
            ste.executeUpdate();
            System.out.println("Emplacement Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Emplacement> afficher() {
        List<Emplacement> emplacements = new ArrayList<>();
        String sql="select * from Emplacement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Emplacement p = new Emplacement(rs.getInt(1),rs.getString("governorat"),rs.getString("Delegation"),rs.getString("type"),rs.getString("adresse"),rs.getString("localite"));
                emplacements.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return emplacements;
    }

    @Override
    public void supprimer(Emplacement t) {
        String sql ="delete from Emplacement where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("L'emplacemnt d'id ="+t.getId()+"a ete supprimer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    @Override
    public void update(Emplacement emplacement){
        String sql = "UPDATE emplacement SET governorat = ?, delegation = ?, type = ?, adresse = ?, localite = ? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, emplacement.getGovernorat());
            ste.setString(2, emplacement.getDelegation());
            ste.setString(3, emplacement.getType());
            ste.setString(4, emplacement.getAdresse());
            ste.setString(5, emplacement.getLocalite());
            ste.setInt(6, emplacement.getId());
            ste.executeUpdate();
            System.out.println("L'emplacement d'id= "+emplacement.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public Emplacement read(int id){
        String sql = "SELECT governorat, delegation, type, adresse, localite FROM emplacement WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Emplacement(id,
                            result.getString("governorat"),
                            result.getString("delegation"),
                            result.getString("type"),
                            result.getString("adresse"),
                            result.getString("localite"));
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
