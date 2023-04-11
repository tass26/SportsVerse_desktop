/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

import java.sql.Array;

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
    private String roles;
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

    public User(int is_verified, int is_banned, String nom, String prenom, String adresse, String num_tel, String email, String roles, String password) {
        this.is_verified = is_verified;
        this.is_banned = is_banned;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
    

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return nom + " " + prenom ;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public int getIs_banned() {
        return is_banned;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
    
    
   
    
}