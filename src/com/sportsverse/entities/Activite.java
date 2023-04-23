/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Activite {
    private int id;
    private String nom;
    private String description;
    private String type;
    private List<Cv> cvs = new ArrayList<>();

    public Activite() {
    }

    public Activite(int id, String nom, String description, String type, List<Cv> cvs) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.cvs = cvs;
    }
    
    public Activite(int id, String nom, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
    }
    
    public Activite(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Cv> getCvs() {
        return cvs;
    }

    public void setCvs(List<Cv> cvs) {
        this.cvs = cvs;
    }

    @Override
    public String toString() {
        return "Activite {" + "id=" + this.id + ", nom=" 
            + this.nom + ", description=" + description 
            + ", type=" + type + ", cvs=" 
            + this.cvs + " } --|####|-- ";
    }
    
    
}
