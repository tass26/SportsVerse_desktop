/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.entities.Reponse;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ReponseService implements IReponse<Reponse>{

Statement stm; 
    Connection conx;
    
    public ReponseService(){
         conx = MaConnection.getInstance().getCnx();
    }
    @Override
    public List<Reponse> afficheListeR() throws SQLException {
          String req = "SELECT * FROM `reponse`";
         stm = conx.createStatement();
         ResultSet rs = stm.executeQuery(req);
         List<Reponse> reponses = new ArrayList<Reponse>();
         while(rs.next()){
             Reponse f = new Reponse();//or rst.getInt(1)
             
                  f.setId  (rs.getInt("id"));
                   f.setid_reclamation( rs.getInt("id_reclamation"));
                      f.setDescription(rs.getString("description"));
                       

                              reponses.add(f);
         }
    
         return reponses;
    }

    @Override
    public void ajouterR(Reponse r,int id) throws SQLException {
        String req ="INSERT INTO `reponse`( `id_reclamation`, `description`) VALUES ('"+id+"','"+r.getDescription()+"')";       stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Reponse ajoutée");
    }

    @Override
    public void modifierO(Reponse o,int id) throws SQLException {
         try {
          String qry = "UPDATE reponse SET `description`='" + o.getDescription()  + "' WHERE id_reclamation=" + id + ";";
               stm = conx.createStatement();
        stm.executeUpdate(qry);
        System.out.println("Reponse modifiée");
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
      public void supprimer(int id) {
try {
            String qry ="DELETE from reponse where id = "+id+";";
            stm = conx.createStatement();
            
            stm.executeUpdate(qry);
            System.out.println("reponse supprimée");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }
      
      
        }
   
    

