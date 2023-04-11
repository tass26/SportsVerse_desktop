/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Chaima
 */
public class Codepromo {
    
    private int id;
    private String code;
    private Date created_At;
    private int valeur;

    public Codepromo() {
    }

    public Codepromo(String code, Date created_At, int valeur) {
        this.code = code;
        this.created_At = created_At;
        this.valeur = valeur;
    }

    public Codepromo(int id, String code, Date created_At, int valeur) {
        this.id = id;
        this.code = code;
        this.created_At = created_At;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public int getValeur() {
        return valeur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Codepromo{" + "id=" + id + ", code=" + code + ", created_At=" + created_At + ", valeur=" + valeur + '}';
    }
    
    
}
