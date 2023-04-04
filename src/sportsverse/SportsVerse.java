/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsverse;
import com.sportsverse.entities.Reclamation;
import com.sportsverse.entities.Reponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sportsverse.services.ReclamationService;
import com.sportsverse.services.ReponseService;
import com.sportsverse.tools.MaConnection;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class SportsVerse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        ReclamationService rs=new ReclamationService();
         ReponseService rl=new ReponseService();

        Date d=new Date();
        Reclamation r= new Reclamation(1,"retard","ttttt","en cours","dali");
       // rs.ajouterR(r);
      // System.out.println(rs.afficheListeR());
       Reclamation r1= new Reclamation(1,1,"retard","reeetttaard","en cours","dali");
      // rs.modifierO(r1);
     // rs.supprimer(1);
        Reponse r4= new Reponse(2,"ttttt");
        Reponse r5= new Reponse(1,2,"tbbbbbb");
        //rl.modifierO(r5);
       // rl.ajouterR(r4);
       rl.supprimer(1);

       

        
    }
    
}
