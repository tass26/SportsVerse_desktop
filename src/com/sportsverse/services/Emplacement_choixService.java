/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.services.*;
import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Emplacement_choix;
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
public class Emplacement_choixService implements NewInterface<Emplacement_choix>{
    Connection cnx;

    public Emplacement_choixService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Emplacement_choix t) {
        String sql="insert into Emplacement_choix (governorat,delegation, localite) values(?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getGovernorat());
            ste.setString(2, t.getDelegation());
            ste.setString(3, t.getLocalite());
            ste.executeUpdate();
            System.out.println("Un nouveau choix d'emplacement Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Emplacement_choix> afficher() {
        List<Emplacement_choix> emplacementschoix = new ArrayList<>();
        String sql="select * from Emplacement_choix";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Emplacement_choix e = new Emplacement_choix(rs.getInt(1),rs.getString("governorat"),rs.getString("Delegation"),rs.getString("localite"));
                emplacementschoix.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return emplacementschoix;    }

    @Override
    public void supprimer(Emplacement_choix t) {
        String sql ="delete from Emplacement_choix where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("le choix d'id = "+t.getId()+" est supprimé avec succés ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    @Override
    public void update(Emplacement_choix t) {
        String sql = "UPDATE emplacement_choix SET governorat = ?, delegation = ?, localite = ? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getGovernorat());
            ste.setString(2, t.getDelegation());
            ste.setString(3, t.getLocalite());
            ste.setInt(4, t.getId());
            ste.executeUpdate();
            System.out.println("Le choix d'id= "+t.getId()+" a ete mis a jour avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    @Override
    public Emplacement_choix read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
