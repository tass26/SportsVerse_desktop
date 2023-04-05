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
public class Emplacement {
    private int id;
    private String governorat;
    private String delegation;
    private String type;
    private String adresse;
    private String localite;

    public Emplacement() {
    }

    public Emplacement(String governorat, String delegation, String type, String adresse, String localite) {
        this.governorat = governorat;
        this.delegation = delegation;
        this.type = type;
        this.adresse = adresse;
        this.localite = localite;
    }
    

    public Emplacement(int id, String governorat, String delegation, String type, String adresse, String localite) {
        this.id = id;
        this.governorat = governorat;
        this.delegation = delegation;
        this.type = type;
        this.adresse = adresse;
        this.localite = localite;
    }

    public int getId() {
        return id;
    }

    public String getGovernorat() {
        return governorat;
    }

    public String getDelegation() {
        return delegation;
    }

    public String getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLocalite() {
        return localite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGovernorat(String governorat) {
        this.governorat = governorat;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    @Override
    public String toString() {
        return "Emplacement{" + "id=" + id + ", governorat=" + governorat + ", delegation=" + delegation + ", type=" + type + ", adresse=" + adresse + ", localite=" + localite + '}';
    }
    
    
    
    
}
