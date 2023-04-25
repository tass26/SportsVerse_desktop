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
            int coach_id = rs.getInt("user_id_id");
            int duree_experience = rs.getInt("duree_experience");
            String certification = rs.getString("certification");

            User coach = new User(coach_id);
            String description = rs.getString("description");
            String image = rs.getString("image");
            String level = rs.getString("level");
            double tarif = rs.getDouble("tarif");
            Cv cv = new Cv(id, coach, certification, description, tarif, image, duree_experience, level, new ArrayList<>());
            cv.setActivites(getActivitesForCv(id));
            return cv;
        }
        return null;
    }
    
    @Override
    public void ajouter(Cv cv) {
        sql = "insert into cv(user_id_id,certification,"
                + "description,tarif,image,"
                + "duree_experience,level)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.getCoach().getId());
            ste.setString(2, cv.getCertification());
            ste.setString(3, cv.getDescription());
            ste.setDouble(4, cv.getTarif());
            ste.setString(5, cv.getImage());
            ste.setInt(6, cv.getDuree_experience());
            ste.setString(7, cv.getLevel());
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
                    us.read(rs.getInt("user_id_id")),
                    rs.getString("certification"),
                    rs.getString("description"),
                    rs.getInt("tarif"),
                    rs.getString("image"),
                    rs.getInt("duree_experience"),
                    rs.getString("level"));
                cvs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cvs;
    }
    
    public List<Activite> getActivitesForCv(int cvId) throws SQLException {
        sql = "SELECT activite.* "
                + "FROM activite "
                + "INNER JOIN cv_activite ON activite.id = cv_activite.activite_id "
                + "INNER JOIN cv ON cv.id = cv_activite.cv_id "
                + "WHERE cv.id = ?;";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ResultSet rs = ste.executeQuery();

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
        sql = "INSERT INTO cv_activite(cv_id, activite_id) VALUES (?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ste.setInt(2, activite.getId());
        ste.executeUpdate();
        System.out.println("activite ajouté a cv avec success !");
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
            System.out.println("cv modifiee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
