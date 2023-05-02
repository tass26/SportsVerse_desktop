/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.entities;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class User {
    private int id;
    private boolean is_verified;
   
 
    private String nom,prenom,adresse,num_tel,email,password,role,is_banned;
  

    public User() {
    }

    public User(int id, boolean is_verified, String nom, String prenom, String adresse, String num_tel, String email, String password, String role, String is_banned) {
        this.id = id;
        this.is_verified = is_verified;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.role = role;
        this.is_banned = is_banned;
    }


    public User(int id, String nom, String prenom, String adresse, String num_tel, String email, String role ,String is_banned) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.role = role;
        this.is_banned = is_banned;
    }

    public User(int id, String nom, String prenom, String adresse, String num_tel, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.role = role;
    }

    public User(String nom, String prenom, String adresse, String num_tel, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.role = role;
    }

   

    public User(String nom, String prenom, String adresse, String num_tel, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.password = password;
        this.role = role;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(String is_banned) {
        this.is_banned = is_banned;
    }

    @Override
    public String toString() {
        return  nom + " " + prenom ;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
}
