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
        User u1 = new User();
//        User u = new User(2,1, 0, "maarfiii", "achreff", "tunis", "22115544", "achref@email.com", "1234");
        Activite a = new Activite("fitness", "fitness description", "fitness type");
        ActiviteService as = new ActiviteService();
//        Cv cv1 = new Cv(u,  "fitness coach certif", "finess coach desc",30, "img.jpg", 5, "pro");
        Cv cv2 = new Cv(u1, "coach fitness certif", "coach fitness description ",30 ,"image.png",5);

        u1.setId(2);
//        Cv cv = new Cv();
//        cv.setCoach(u);
//        cv.setDescription("coach desc");
//        cv.setCertification("coach certif");
//        cv.setLevel("pro");
        CvService cs = new CvService();
            System.out.println("---- ajouter cv ----");

         cs.ajouter(cv2);
            System.out.println("---- afficher cvs ----");
//

        System.out.println(cs.afficher());
        Activite a1= new Activite();
        a1.setId(5);
            System.out.println("---- ajouter activite ----");

//        as.ajouter(a);
        System.out.println("---- afficher activite by ID ----");
//
        try {
            System.out.println( as.getActiviteById(1));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("---- afficher activite ----");
        System.out.println(as.afficher());

            System.out.println("---- add cv to activite ----");
        try {
            as.addCvToActivite(2, a1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("---- list activites of cv ----");

        try {
            System.out.println(cs.getActivitesForCv(2));
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }


    }
    
}
