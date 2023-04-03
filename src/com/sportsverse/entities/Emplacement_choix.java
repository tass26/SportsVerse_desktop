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
public class Emplacement_choix {
    private int id;
    private String governorat;
    private String delegation;
    private String localite;

    public Emplacement_choix() {
    }

    public Emplacement_choix(String governorat, String delegation, String localite) {
        this.governorat = governorat;
        this.delegation = delegation;
        this.localite = localite;
    }

    public Emplacement_choix(int id, String governorat, String delegation, String localite) {
        this.id = id;
        this.governorat = governorat;
        this.delegation = delegation;
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

    public void setLocalite(String localite) {
        this.localite = localite;
    }
    
    
}
