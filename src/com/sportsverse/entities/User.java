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

    public User(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
}
