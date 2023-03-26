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
public class Cv {
    private int id ;
    private int duree_experience;
    private User u;
    private String certification;
    private String description;
    private String image;
    private String level ;
    private double tarif;

    public Cv() {
    }

    public Cv(int id, int duree_experience, User u, String certification, String description, String image, String level, double tarif) {
        this.id = id;
        this.duree_experience = duree_experience;
        this.u = u;
        this.certification = certification;
        this.description = description;
        this.image = image;
        this.level = level;
        this.tarif = tarif;
    }

    public int getId() {
        return id;
    }

    public int getDuree_experience() {
        return duree_experience;
    }

    public User getU() {
        return u;
    }

    public String getCertification() {
        return certification;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLevel() {
        return level;
    }

    public double getTarif() {
        return tarif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuree_experience(int duree_experience) {
        this.duree_experience = duree_experience;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
    
    
}
