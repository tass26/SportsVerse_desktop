/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.tests;

import com.sportsverse.entities.Cv;
import com.sportsverse.services.CvService;
import com.sportsverse.tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cv cv = new Cv(5,  "fitness coach certif", "finess coach desc", "img.jpg", "pro", 30);
        CvService cs = new CvService();
         cs.ajouter(cv);
        
        System.out.println(cs.afficher());
     MaConnection m =MaConnection.getInstance();
    }
    
}
