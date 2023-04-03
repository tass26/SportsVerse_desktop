/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Seance {
    private int id;
    private User C;
    private Emplacement E;
    private Date date;
    private String etat;
    private String duree;
    private String adresse_client;
    private String message;

    public Seance() {
    }

    public Seance(User C, Emplacement E, Date date, String etat, String duree, String adresse_client, String message) {
        this.C = C;
        this.E = E;
        this.date = date;
        this.etat = etat;
        this.duree = duree;
        this.adresse_client = adresse_client;
        this.message = message;
    }

    public Seance(int id, User C, Emplacement E, Date date, String etat, String duree, String adresse_client, String message) {
        this.id = id;
        this.C = C;
        this.E = E;
        this.date = date;
        this.etat = etat;
        this.duree = duree;
        this.adresse_client = adresse_client;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public User getC() {
        return C;
    }

    public Emplacement getE() {
        return E;
    }

    public Date getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public String getDuree() {
        return duree;
    }

    public String getAdresse_client() {
        return adresse_client;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setC(User C) {
        this.C = C;
    }

    public void setE(Emplacement E) {
        this.E = E;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setAdresse_client(String adresse_client) {
        this.adresse_client = adresse_client;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
