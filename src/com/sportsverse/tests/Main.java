/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.tests;

import com.sportsverse.entities.User;
import com.sportsverse.service.EmplacementService;
import com.sportsverse.service.UserService;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        EmplacementService ps = new EmplacementService();
        UserService us = new UserService();
        
//        SeanceService ss= new SeanceService();
//        Emplacement p = new Emplacement(2,"Tunis","Sidi Hssin","Maison","17 rue de bulgarie","jayara");
//        User u = us.read(1);
//        Date dateres=Date.valueOf("2023-04-03");
//        Seance s = new Seance(1,u,p,dateres,"en attente", "1 heure", u.getAdresse(), "en se voit au prés de kiosque total");
//        ss.ajouter(s);
//        ss.afficher();
            
//        User u = new User("sarah", "mejri", "tunis","52505287","mp@gmail.com","123456789","[ROLE_ADMIN]");
//        us.ajouterUser(u);
        //us.afficherUsers();
//          us.signIn("ff@gmail.com","123456789");
          us.blockage(19);
//        us.changePassword("ff@gmail.com","123456","123456789");
       // us.modifierUser(8, "sarah", "sarah", "tunis", "52222333", "jj@gmail.com");
        //us.supprimerUser(7);
        //ps.ajouter(p);
       
        //ps.supprimer(p);
         //System.out.println( ps.afficher());
         //System.out.println(ps.read(2));
    }
    
}
