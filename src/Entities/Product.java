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
public class Product {
    private int id;
    private int categorie_id;
    private String nom_produit;
    private Double prix_ttc;
    private int quantite;
    private String image;
    private int quantiteincart;
    

    public Product() {
    }

    public Product(int id, int categorie_id, String nom_produit, Double prix_ttc, int quantite, String image) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.prix_ttc = prix_ttc;
        this.quantite = quantite;
        this.image = image;
    }

    public Product(int categorie_id, String nom_produit, Double prix_ttc, int quantite, String image) {
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.prix_ttc = prix_ttc;
        this.quantite = quantite;
        this.image = image;
    }

    public Product(int id, int categorie_id, String nom_produit, Double prix_ttc, int quantite, String image, int quantiteincart) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.prix_ttc = prix_ttc;
        this.quantite = quantite;
        this.image = image;
        this.quantiteincart = quantiteincart;
    }
    

    public int getId() {
        return id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public Double getPrix_ttc() {
        return prix_ttc;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setPrix_ttc(Double prix_ttc) {
        this.prix_ttc = prix_ttc;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categorie_id=" + categorie_id + ", nom_produit=" + nom_produit + ", prix_ttc=" + prix_ttc + ", quantite=" + quantite + ", image=" + image + ", quantiteincart=" + quantiteincart + '}';
    }



    public int getQuantiteincart() {
        return quantiteincart;
    }

    public void setQuantiteincart(int quantiteincart) {
        this.quantiteincart = quantiteincart;
    }
    
    
}
