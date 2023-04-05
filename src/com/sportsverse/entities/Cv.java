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
public class Cv {
    private int id ;
    private int duree_experience;
    private User coach;
    private List<Activite> activites = new ArrayList<>();
    private String certification;
    private String description;
    private String image;
    private String level ;
    private double tarif;

    public Cv() {
    }

    public Cv(int id, int duree_experience, User coach, List<Activite> activites, String certification, String description, String image, String level, double tarif) {
        this.id = id;
        this.duree_experience = duree_experience;
        this.coach = coach;
        this.activites = activites;
        this.certification = certification;
        this.description = description;
        this.image = image;
        this.level = level;
        this.tarif = tarif;
    }
    
    public Cv(int id, int duree_experience, User coach, String certification, String description, String image, String level, double tarif) {
        this.id = id;
        this.duree_experience = duree_experience;
        this.coach = coach;
        this.certification = certification;
        this.description = description;
        this.image = image;
        this.level = level;
        this.tarif = tarif;
    }

    public Cv(int duree_experience, User coach, String certification, String description, String image, String level, double tarif) {
        this.duree_experience = duree_experience;
        this.coach = coach;
        this.certification = certification;
        this.description = description;
        this.image = image;
        this.level = level;
        this.tarif = tarif;
    }
    
    public Cv(int duree_experience,String certification, String description, String image, String level, double tarif) {
        this.duree_experience = duree_experience;
        
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

    public User getCoach() {
        return coach;
    }
    
    public void setCoach(User u) {
        this.coach = coach;
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

    
    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public void addActivite(Activite activite) {
        activites.add(activite);
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
