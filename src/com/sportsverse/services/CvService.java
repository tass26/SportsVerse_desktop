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

/**
 *
 * @author Achref
 */

public class CvService implements NewInterface<Cv>{
    String sql;
    Connection cnx;
    UserService us = new UserService();

    public CvService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    public Cv getCvById(int id) throws SQLException {
        sql = "SELECT * FROM cv WHERE id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
            int coach_id = rs.getInt("coach_id");
            int duree_experience = rs.getInt("duree_experience");
            User coach = new User(coach_id);
            String certification = rs.getString("certification");
            String description = rs.getString("description");
            String image = rs.getString("image");
            String level = rs.getString("level");
            double tarif = rs.getDouble("tarif");
            Cv cv = new Cv(id, coach, duree_experience,  new ArrayList<>(), certification, description, image, level, tarif);
            cv.setActivites(getActivitesForCv(id));
            return cv;
        }
        return null;
    }
    
    @Override
    public void ajouter(Cv cv) {
        sql = "insert into cv(coach_id,duree_experience,certification,"
                + "description,image,level,tarif)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.getCoach().getId());
            ste.setInt(2, cv.getDuree_experience());
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
                    us.read(rs.getInt("coach")),
                    rs.getInt("duree_experience"),
                    rs.getString("certification"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getString("level"),
                    rs.getDouble("tarif"));
                cvs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cvs;
    }
    
    public List<Activite> getActivitesForCv(int cvId) throws SQLException {
        sql = "SELECT * FROM activite WHERE cv_id = ?";
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
        sql = "INSERT INTO activite (cv_id, nom) VALUES (?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ste.setString(2, activite.getNom());
        ste.executeUpdate();
    }

    @Override
    public void supprimer(Cv t) {
         
    }

    @Override
    public Cv read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cv cv) {
         sql = "UPDATE cv SET coach,duree_experience,certification,"
                + "description,image,level,tarif)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.getCoach().getId());
            ste.setInt(2, cv.getDuree_experience());
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
    
}
