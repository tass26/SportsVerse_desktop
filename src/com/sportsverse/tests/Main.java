/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.tests;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Seance;
import com.sportsverse.entities.User;
import com.sportsverse.service.EmplacementService;
import com.sportsverse.service.SeanceService;
import com.sportsverse.service.UserService;
import com.sportsverse.tools.MaConnection;
import java.sql.Date;

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
        EmplacementService ps = new EmplacementService();
        UserService us = new UserService();
        SeanceService ss= new SeanceService();
        Emplacement p = new Emplacement(2,"Tunis","Sidi Hssin","Maison","17 rue de bulgarie","jayara");
        User u = us.read(1);
        Date dateres=Date.valueOf("2023-04-04");
        Seance s = new Seance(u,p,dateres,"en attente", "1 heure", u.getAdresse(), "en se voit au prés de kiosque total");
        ss.ajouter(s);
        ss.afficher();
        //ps.ajouter(p);
       
        //ps.supprimer(p);
        //System.out.println( ps.afficher());
        //System.out.println(ps.read(2));
    }
    
}
