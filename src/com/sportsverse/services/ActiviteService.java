/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.entities.Activite;
import com.sportsverse.entities.Cv;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achref
 */
public class ActiviteService implements NewInterface<Activite>{
    String sql;
    Connection cnx;
    public ActiviteService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    public Activite getActiviteById(int ActiviteId) throws SQLException {
        String sql = "SELECT * FROM activite WHERE id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, ActiviteId);
        ResultSet rs = ste.executeQuery();
        List<Cv> cvs = new ArrayList<>();

        if (rs.next()) {
            int duree_experience = rs.getInt("duree_experience");
            String certification = rs.getString("certification");
            String description = rs.getString("description");
            String level = rs.getString("type");
            Cv cv = new Cv(id, duree_experience, u, activites, certification, description, level, level, id);
            cvs.add(cv);
            return cv;
        }
        return null;
    }
    
    @Override
    public void ajouter(Activite activite) {
        sql = "insert into cv(nom,description,type)"
                + " values(?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, cv.getNom());
            ste.setString(4, cv.getDescription());
            ste.setString(5, cv.getType());
            ste.executeUpdate();
            System.out.println("Activite ajoutee !");
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Activite> afficher() {
        List<Activite> activites = new ArrayList<>();
        sql="select * from activite";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Activite a = new Activite(
                    rs.getInt(1),
                    rs.getString(3),
                    rs.getString(3));
                activites.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }
    
    public List<Activite> getCvsForActivite(int activiteId) throws SQLException {
        String sql = "SELECT * FROM activite WHERE cv_id = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setInt(1, cvId);
        ResultSet rs = stmt.executeQuery();

        List<Activite> activites = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            String type = rs.getString("type");
            Activite activite = new Activite(id, nom, description, type);
            activites.add(activite);
        }
        return activites;
    }
    
    public void addCvToActivite(int cvId, Activite activite) throws SQLException {
        String sql = "INSERT INTO activite (cv_id, nom) VALUES (?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ste.setString(2, activite.getNom());
        ste.executeUpdate();
    }

    @Override
    public void supprimer(Activite t) {
         
    }
}
