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
public class Produit {
    private int id;
    private int quantite;
    private Categorie C;
    private String nom_produit;
    private String image;
    private double prix_ttc;

    public Produit() {
    }

    public Produit(int id, int quantite, Categorie C, String nom_produit, String image, double prix_ttc) {
        this.id = id;
        this.quantite = quantite;
        this.C = C;
        this.nom_produit = nom_produit;
        this.image = image;
        this.prix_ttc = prix_ttc;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public Categorie getC() {
        return C;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getImage() {
        return image;
    }

    public double getPrix_ttc() {
        return prix_ttc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setC(Categorie C) {
        this.C = C;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix_ttc(double prix_ttc) {
        this.prix_ttc = prix_ttc;
    }
    
    
    
    
}
