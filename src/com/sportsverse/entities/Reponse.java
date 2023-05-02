/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

/**
 *
 * @author ASUS
 */
public class Reponse {
    private int id ;
    private int id_reclamation ; 
    private String description;

    public Reponse() {
    }

    public Reponse(int id_reclamation, String description) {
        this.id_reclamation = id_reclamation;
        this.description = description;
    }

    public Reponse(int id, int id_reclamation , String description) {
        this.id = id;
        this.id_reclamation = id_reclamation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getid_reclamation() {
        return id_reclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setid_reclamation(int id_reclamation) {
        this.id_reclamation =id_reclamation ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", id_reclamation=" + id_reclamation + ", description=" + description + '}';
    }
    
}
