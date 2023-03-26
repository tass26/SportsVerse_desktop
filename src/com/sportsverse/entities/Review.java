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
public class Review {
    private int id;
    private int nbre;
    private Produit p;
    private User u;
    private Date date_creation;
    private String Comment;

    public Review() {
    }

    public Review(int id, int nbre, Produit p, User u, Date date_creation, String Comment) {
        this.id = id;
        this.nbre = nbre;
        this.p = p;
        this.u = u;
        this.date_creation = date_creation;
        this.Comment = Comment;
    }

    public int getId() {
        return id;
    }

    public int getNbre() {
        return nbre;
    }

    public Produit getP() {
        return p;
    }

    public User getU() {
        return u;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public String getComment() {
        return Comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
  
    
}
