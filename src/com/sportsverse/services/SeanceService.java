/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.services.*;
import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Seance;
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
public class SeanceService implements NewInterface<Seance>{
    Connection cnx;
    EmplacementService ps = new EmplacementService();
    UserService us = new UserService();

    public SeanceService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Seance t) {
        String sql="insert into Seance(coach_id_id,emplacement_id,date,etat,duree,adresse_client,message) values(?,?,?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getC().getId());
            ste.setInt(2, t.getE().getId());
            ste.setDate(3,t.getDate() );
            ste.setString(4, t.getEtat());
            ste.setString(5, t.getDuree());
            ste.setString(6, t.getAdresse_client());
            ste.setString(7, t.getMessage());
            ste.executeUpdate();
            System.out.println("Seance Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Seance> afficher() {
        List<Seance> Seances = new ArrayList<>();
        String sql="select * from Seance";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Seance p = new Seance(
                        rs.getInt(1),
                        us.read(rs.getInt("coach_id_id")),
                        ps.read(rs.getInt("emplacement_id")),
                        rs.getDate("date"),
                        rs.getString("etat"),
                        rs.getString("duree"),
                        rs.getString("adresse_client"),
                        rs.getString("message"));
                Seances.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return Seances;
    }

    @Override
    public void supprimer(Seance t) {
        String sql ="delete from Seance where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("La seance d'id ="+t.getId()+"a ete supprimer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     }

    @Override
    public void update(Seance t) {
        String sql = "UPDATE Seance SET coach_id_id=?, emplacement_id=?, date=?, etat=?, duree=?, adresse_client=?, message=?  WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getC().getId());
            ste.setInt(2, t.getE().getId());
            ste.setDate(3, t.getDate());
            ste.setString(4, t.getEtat());
            ste.setString(5, t.getDuree());
            ste.setString(6, t.getAdresse_client());
            ste.setString(7, t.getMessage());
            ste.setInt(8, t.getId());
            ste.executeUpdate();
            System.out.println("L'emplacement d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public Seance read(int id) {
        String sql = "SELECT coach_id_id, emplacement_id, date, etat, duree, adresse_client, message FROM seance WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Seance(id,
                    us.read(result.getInt("coach_id_id")),
                    ps.read(result.getInt("emplacement")),
                    result.getDate("date"),
                    result.getString("etat"),
                    result.getString("duree"),
                    result.getString("adresse_client"),
                    result.getString("message"));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;    }
        public List<Seance> getAllSeance(int id){
        List<Seance> seances = new ArrayList<>();
        String sql="SELECT * from seance s , emplacement E WHERE s.emplacement_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                Seance p = new Seance(rs.getInt(1),us.read(rs.getInt("coach_id_id")),ps.read(rs.getInt("emplacement_id")),rs.getDate("date"),rs.getString("etat"),rs.getString("duree"),rs.getString("adresse_client"),rs.getString("message"));
                seances.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return seances;
    }
    
}
