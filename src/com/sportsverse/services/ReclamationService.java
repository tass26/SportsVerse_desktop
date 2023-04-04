/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sportsverse.tools.MaConnection;
import com.sportsverse.entities.Reclamation;

/**
 *
 * @author ASUS
 */
public class ReclamationService implements IReclamation<Reclamation>{
Statement stm; 
    Connection conx;
    
    public ReclamationService(){
         conx = MaConnection.getInstance().getCnx();
    }
    @Override
    public List<Reclamation> afficheListeR() throws SQLException {
   String req = "SELECT * FROM `reclamation`";
         stm = conx.createStatement();
         ResultSet rs = stm.executeQuery(req);
         List<Reclamation> reclamations = new ArrayList<Reclamation>();
         while(rs.next()){
             Reclamation f = new Reclamation();//or rst.getInt(1)
             
                  f.setId  (rs.getInt("id"));
                   f.setid_user( rs.getInt("id_user"));
                    f.setSujet(rs.getString("sujet"));
                      f.setDescription(rs.getString("description"));
                        f.setEtat(rs.getString("etat"));
                         f.setDate(rs.getDate("date"));
                          f.setNom_client(rs.getString("nom_client"));

                              reclamations.add(f);
         }
    
         return reclamations;
    }

    @Override
    public void ajouterR(Reclamation r) throws SQLException {
String req ="INSERT INTO `reclamation`(`id_user`, `sujet`, `description`, `etat`,  `nom_client`) VALUES ('"+r.getid_user()+"','"+r.getSujet()+"','"+r.getDescription()+"','"+r.getEtat()+"','"+r.getNom_client()+"')";   
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Reclamation ajoutée");
    
}

    @Override
    public void modifierO(Reclamation o) throws SQLException {
    try {
          String qry = "UPDATE reclamation SET id_user='" + o.getid_user() + "', sujet='" + o.getSujet() + "', description='" + o.getDescription() + "', etat='" + o.getEtat() + "', nom_client='" + o.getNom_client() + "' WHERE id=" + o.getId() + ";";
               stm = conx.createStatement();
        stm.executeUpdate(qry);
        System.out.println("Reclamation modifiée");
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    public void supprimer(int id) {
try {
            String qry ="DELETE from reclamation where id = "+id+";";
            stm = conx.createStatement();
            
            stm.executeUpdate(qry);
            System.out.println("reclamation supprimée");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }
    }
    

