package com.sportsverse.services;

import com.sportsverse.entities.Categorie;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class CategorieService {
  
    private Connection cnx = MaConnection.getInstance().getCnx();

   
    
    public boolean controleNomcat(String nomcategorie) {
        return (nomcategorie.length() > 0);
    }

    /**
     *
     * @param c
     */
   
    public void ajouterCat(Categorie c) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(
                    "INSERT INTO categorie (nom) VALUES (?)");
            preparedStatement.setString(1, c.getNom());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("Categorie ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Categorie> DisplayCat() {

        List<Categorie> myList = new ArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM categorie");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                myList.add(new Categorie(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'affichage (tout) categories : " + e.getMessage());
        }
        return myList;

    }

    
    public void SupprimerCat(int id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement("DELETE FROM `categorie` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Categorie supprimé");
        } catch (SQLException e) {
            System.out.println("Erreur de suppresion categorie : " + e.getMessage());
        }
    }

    
    public void ModifierCat(Categorie c) {
        {
            PreparedStatement preparedStatement;

            try {
                preparedStatement = cnx.prepareStatement(
                        "UPDATE `categorie` "
                        + "SET `nom` = ?"
                        + "WHERE `id` = ?");
                preparedStatement.setString(1, c.getNom());
                preparedStatement.setInt(2, c.getId());
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Erreur de modification categorie : " + e.getMessage());
            }

        }
    }  
     public Categorie read(int id){
        String sql = "SELECT nom FROM Categorie WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Categorie(id,
                            result.getString("nom"));
                            
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
