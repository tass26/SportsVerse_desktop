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
    
    public Activite getActiviteById(int id) throws SQLException {
        sql = "SELECT * FROM activite WHERE id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        List<Cv> cvs = new ArrayList<>();

        if (rs.next()) {
            
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            String type = rs.getString("type");
            Activite activite = new Activite(id, nom, description, type);
            activite.setCvs(getCvsForActivite(id));
            return activite;
        }
        return null;
    }
    
    @Override
    public void ajouter(Activite activite) {
        sql = "insert into activite(nom,description,type)"
                + " values(?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, activite.getNom());
            ste.setString(2, activite.getDescription());
            ste.setString(3, activite.getType());
            ste.executeUpdate();
            System.out.println("Activite ajoutee !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
                    rs.getString(2),

                    rs.getString(3),
                    rs.getString(4));
                activites.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }
    
    public List<Cv> getCvsForActivite(int activiteId) throws SQLException {
        sql = "SELECT * FROM cv_activite WHERE activite_id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, activiteId);
        ResultSet rs = ste.executeQuery();

        List<Cv> cvs = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int duree_experience = rs.getInt("duree_experience");
            int coach_id = rs.getInt("coach_id");
            User coach = new User(coach_id);
            String certification = rs.getString("certification");
            String description = rs.getString("description");
            String image = rs.getString("image");
            String level = rs.getString("level");
            double tarif = rs.getDouble("tarif");
            Cv cv = new Cv(id, coach, certification, description, tarif, image, duree_experience, level, new ArrayList<>());
            cvs.add(cv);
        }
        return cvs;
    }
    
    public void addCvToActivite(int cvId, Activite activite) throws SQLException {
        sql = "INSERT INTO cv_activite(cv_id, activite_id) VALUES (?, ?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, cvId);
        ste.setInt(2, activite.getId());
        ste.executeUpdate();
        System.out.println("cv ajouté a activité avec success !");
    }

    @Override
    public void supprimer(Activite t) {
         
    }

    @Override
    public Activite read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Activite t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
