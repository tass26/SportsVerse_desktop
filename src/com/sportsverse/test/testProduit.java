/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.test;

import com.sportsverse.entities.Categorie;
import com.sportsverse.entities.Produit;
import com.sportsverse.services.CategorieService;
import com.sportsverse.services.ProduitService;

/**
 *
 * @author user
 */
public class testProduit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Categorie c2=new Categorie("sport1");
        CategorieService cc=new CategorieService();
       // cc.ajouterCat(c2);
      Categorie c1 =cc.read(1);
       Produit p=new Produit(10, c1, "gants", "image", 150);
       ProduitService pp=new ProduitService();
       cc.DisplayCat();
      //System.out.println( pp.afficher());
        //System.out.println(pp.getCategorieById(1));
        System.out.println( pp.getProduitByCategorie(1));
        System.out.println(pp.getProduitByTitre("robe"));
      //  System.out.println(pp.afficher());
       //pp.ajouter(p);
    }
    
}
