/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    private int id ;
    private int id_user ;
    private String sujet;
    private String description;
    private String etat;
    private Date date ;
    private String nom_client;

    public Reclamation() {
    }

    public Reclamation(int id_user, String sujet, String description, String etat,  String nom_client) {
        this.id_user = id_user;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
      //  this.date = date;
        this.nom_client = nom_client;
    }
    

    public Reclamation(int id, int id_user, String sujet, String description, String etat,  String nom_client) {
        this.id = id;
        this.id_user = id_user;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        //this.date = date;
        this.nom_client = nom_client;
    }

    public int getId() {
        return id;
    }

    public  int getid_user() {
        return id_user;
    }

    public String getSujet() {
        return sujet;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate() {
        return date;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setid_user(int id_user) {
        this.id_user = id_user;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user=" + id_user + ", sujet=" + sujet + ", description=" + description + ", etat=" + etat + ", date=" + date + ", nom_client=" + nom_client + '}';
    }
    
    
}
