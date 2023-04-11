/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.tests;

import com.sportsverse.entities.Activite;
import com.sportsverse.entities.Cv;
import com.sportsverse.entities.User;
import com.sportsverse.services.ActiviteService;
import com.sportsverse.services.CvService;
import com.sportsverse.services.EmplacementService;
import com.sportsverse.services.SeanceService;
import com.sportsverse.services.UserService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
         
        UserService us = new UserService();
        System.out.println("---- read user ----");

        User u = us.read(1);
        Activite a = new Activite("fitness", "fitness description", "fitness type");
        ActiviteService as = new ActiviteService();
        Cv cv = new Cv(u, 2,  "fitness coach certif", "finess coach desc", "img.jpg", "pro", 30);
        CvService cs = new CvService();
                System.out.println("---- ajouter cv ----");

         cs.ajouter(cv);
//                System.out.println("---- afficher cvs ----");
//
//        System.out.println(cs.afficher());
            System.out.println("---- ajouter activite ----");

        as.ajouter(a);
        try {
            System.out.println( as.getActiviteById(1));
            
            //ps.supprimer(p);
            //System.out.println( ps.afficher());
            //System.out.println(ps.read(2));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
