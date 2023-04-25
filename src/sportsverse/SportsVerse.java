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
        Reclamation r2= new Reclamation(1,"retardd","ttttt","en cours","dali");
      
      // System.out.println(rs.afficheListeR());
      // Reclamation r1= new Reclamation(1,1,"retard","reeetttaard","en cours","dali");
      // rs.modifierO(r1);
     
      //  Reponse r4= new Reponse(2,"ttttt");
       Reponse r6= new Reponse(1,26,"titou");
        //rl.modifierO(r5);
       // rl.ajouterR(r6);
       rl.supprimer(41);
          //   Reclamation r3= new Reclamation(1,r"","t","en cours","samira");
              // rs.ajouterR(r3);
//              rs.ajouterR(r2);
     // rs.supprimer(2);

       

        
    }
    
}
