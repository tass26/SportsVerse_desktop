/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.tests;

import com.sportsverse.entities.Cv;
import com.sportsverse.entities.User;
import com.sportsverse.services.CvService;
import com.sportsverse.services.EmplacementService;
import com.sportsverse.services.SeanceService;
import com.sportsverse.services.UserService;

/**
 *
 * @author Achref
 */
public class CvTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         EmplacementService ps = new EmplacementService();
        UserService us = new UserService();
        SeanceService ss= new SeanceService();
        User u = us.read(1);
      
        Cv cv = new Cv(u, 2,  "fitness coach certif", "finess coach desc", "img.jpg", "pro", 30);
        CvService cs = new CvService();
         cs.ajouter(cv);
        
        System.out.println(cs.afficher());
        //ps.ajouter(p);
       
        //ps.supprimer(p);
         //System.out.println( ps.afficher());
         //System.out.println(ps.read(2));

    }
    
}
