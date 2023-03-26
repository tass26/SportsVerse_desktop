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
public class Commande {
    private int id ;
    private Date date ;
    private String etat;
    private User u;

    public Commande() {
    }

    public Commande(int id, Date date, String etat, User u) {
        this.id = id;
        this.date = date;
        this.etat = etat;
        this.u = u;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public User getU() {
        return u;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setU(User u) {
        this.u = u;
    }
    

    
    
}
