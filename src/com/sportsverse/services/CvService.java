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
public class CvService implements NewInterface<Cv>{
    String sql;
    Connection cnx;
    public CvService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    public Cv getCvById(int id) throws SQLException {
        String sql = "SELECT * FROM cv WHERE id = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int duree_experience = rs.getInt("duree_experience");
            int user_id = rs.getInt("user_id");
            User u = new User(user_id);
            String certification = rs.getString("certification");
            String description = rs.getString("description");
            String image = rs.getString("image");
            String level = rs.getString("level");
            double tarif = rs.getDouble("tarif");
            Cv cv = new Cv(id, duree_experience, u, new ArrayList<Activite>(), certification, description, image, level, tarif);
            cv.setActivites(getActivitesForCv(id));
            return cv;
        }
        return null;
    }
    
    @Override
    public void ajouter(Cv cv) {
        sql = "insert into cv(duree_experience,u,certification,"
                + "description,image,level,tarif)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.getDuree_experience());
            ste.setInt(2, cv.getU().getId());
            ste.setString(3, cv.getCertification());
            ste.setString(4, cv.getDescription());
            ste.setString(5, cv.getImage());
            ste.setString(6, cv.getLevel());
            ste.setDouble(7, cv.getTarif());
            ste.executeUpdate();
            System.out.println("cv ajoutee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Cv> afficher() {
        List<Cv> cvs = new ArrayList<>();
        sql="select * from cv";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Cv c = new Cv(
                    rs.getInt(1),
                    
                    rs.getString(3),
                    rs.getString(3),
                    rs.getString(3),
                    rs.getString(3),
                    rs.getDouble(4));
                cvs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cvs;
    }
    
    public List<Activite> getActivitesForCv(int cvId) throws SQLException {
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
    
    public void addActiviteToCv(int cvId, Activite activite) throws SQLException {
        String sql = "INSERT INTO activite (cv_id, nom) VALUES (?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ste.setString(2, activite.getNom());
        ste.executeUpdate();
    }

    @Override
    public void supprimer(Cv t) {
         
    }
    
}
