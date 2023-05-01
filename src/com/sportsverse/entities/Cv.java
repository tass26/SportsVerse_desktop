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
    private User coach;
    private String certification;
    private String description;
    private List<Activite> activites = new ArrayList<>();
    private double tarif;
    private String image;
    private int duree_experience;

    public Cv() {
    }

    public Cv(int id, User coach, String certification, String description,double tarif, String image, int duree_experience, List<Activite> activites ) {
        this.id = id;        
        this.coach = coach;
        this.certification = certification;
        this.description = description;
        this.tarif = tarif;
        this.image = image;
        this.duree_experience = duree_experience;
        this.activites = activites;

    }
    
    public Cv(int id, User coach, String certification, String description, double tarif, String image, int duree_experience) {
        this.id = id;        
        this.coach = coach;
        this.certification = certification;
        this.description = description;
        this.tarif = tarif;
        this.image = image;
        this.duree_experience = duree_experience;
    }

    public Cv( User coach, String certification, String description, double tarif, String image, int duree_experience) {
        this.coach = coach;
        this.certification = certification;
        this.description = description;
        this.tarif = tarif;
        this.image = image;
        this.duree_experience = duree_experience;
    }
    
    public Cv(String certification, String description, double tarif, String image, int duree_experience) {
        this.certification = certification;
        this.description = description;
        this.tarif = tarif;
        this.image = image;
        this.duree_experience = duree_experience;
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

    public String getCertification() {
        return certification;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
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

    public void setCoach(User coach) {
        this.coach = coach;
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

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "Cv{" + "id=" + id + 
            ", coach nom & prenom= " + coach.getNom() +" "+coach.getPrenom() 
            + ", certification=" + certification + ", description=" + 
            description + ", activites=" + activites + ", tarif=" + tarif 
            +", image=" + image + ", duree_experience=" + duree_experience 
            + "'}' --|####|-- ";
    }
    
    
}
