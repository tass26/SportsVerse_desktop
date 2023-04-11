/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsverse;

import Entities.Codepromo;
import Entities.Commande;
import Services.CodepromoService;
import Services.CommandeService;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Chaima
 */
public class SportsVerse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Commande c = new Commande(1, 1, new Date(0), "AB");
        CommandeService cs = new CommandeService();

        //cs.add(c);
        // cs.update(c, 3);
        // System.out.println(cs.show());
       //  cs.delete(7);
       
        
        
//        Codepromo cp = new Codepromo("2023FEV", new Date(0), 80);
//        CodepromoService cps = new CodepromoService();
        
        
        //cps.add(cp);
        //cps.update(cp, 2);
        //System.out.println(cps.show());
        // cps.delete(2);
        
                    Codepromo cp = new Codepromo("", new Date(new java.util.Date(System.currentTimeMillis()).getTime()) , 50   );
            CodepromoService cps = new CodepromoService();
            cps.add(cp);
        
        
    }

}
