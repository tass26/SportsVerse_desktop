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
public class User {
    private int id;
    private int is_verified;
    private int is_banned;
    private String nom;
    private String prenom;
    private String adresse;
    private String num_tel;
    private String email;
    private String password;

    public User() {
    }

    public User(int is_verified, int is_banned, String nom, String prenom, String adresse, String num_tel, String email, String password) {
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
    }

    public User(int id, int is_verified, int is_banned, String nom, String prenom, String adresse, String num_tel, String email, String password) {
        this.id = id;
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }
    
    
    
   
    
}
