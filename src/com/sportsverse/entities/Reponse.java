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
    private Reclamation R;
    private String description;

    public Reponse() {
    }

    public Reponse(int id, Reclamation R, String description) {
        this.id = id;
        this.R = R;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Reclamation getR() {
        return R;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setR(Reclamation R) {
        this.R = R;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
