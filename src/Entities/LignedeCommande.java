/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Chaima
 */
public class LignedeCommande {
    private int id; 
    private int idP; // id Produit
    private int idC; // id Commande
    private int Qte;

    public LignedeCommande() {
    }

    public LignedeCommande(int idP, int idC, int Qte) {
        this.idP = idP;
        this.idC = idC;
        this.Qte = Qte;
    }

    public int getId() {
        return id;
    }

    public int getIdP() {
        return idP;
    }

    public int getIdC() {
        return idC;
    }

    public int getQte() {
        return Qte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }
    
    
    
}
