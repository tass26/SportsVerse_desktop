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
public class Commande {
    private int id;
    private int CreatedBy;
    private int code_id;
    private Date date;
    private String etat;

    public Commande() {
    }

    public Commande(int id, int CreatedBy, int code_id, Date date, String etat) {
        this.id = id;
        this.CreatedBy = CreatedBy;
        this.code_id = code_id;
        this.date = date;
        this.etat = etat;
    }
    

    public Commande(int CreatedBy, int code_id, Date date, String etat) {
        this.CreatedBy = CreatedBy;
        this.code_id = code_id;
        this.date = date;
        this.etat = etat;
    }

    public int getCode_id() {
        return code_id;
    }

    public void setCode_id(int code_id) {
        this.code_id = code_id;
    }

    

    
    public int getId() {
        return id;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public Date getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", CreatedBy=" + CreatedBy + ", date=" + date + ", etat=" + etat + '}';
    }
    
    
    
}
