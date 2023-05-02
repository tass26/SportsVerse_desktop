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
import com.sportsverse.entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                   f.setid_user( rs.getInt("user_id_id"));
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
String req ="INSERT INTO `reclamation`(`user_id_id`, `sujet`, `description`, `etat`,  `nom_client`) VALUES ('"+r.getid_user()+"','"+r.getSujet()+"','"+r.getDescription()+"','"+r.getEtat()+"','"+r.getNom_client()+"')";   
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Reclamation ajoutée");
    
}

    @Override
    public void modifierO(Reclamation o,int id) throws SQLException {
    try {
          String qry = "UPDATE reclamation SET user_id_id='" + o.getid_user() + "', sujet='" + o.getSujet() + "', description='" + o.getDescription() + "', etat='" + o.getEtat() + "', nom_client='" + o.getNom_client() +"' WHERE id="+id;
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
    public List<Reclamation> getAllReclamationsByUserId(int userId) throws SQLException {
    List<Reclamation> reclamations = new ArrayList<>();

    String query = "SELECT * FROM reclamation WHERE user_id_id = ?";
    PreparedStatement pst = conx.prepareStatement(query);
    pst.setInt(1, userId);
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {
        Reclamation r = new Reclamation();
        r.setId(rs.getInt("id"));
        r.setid_user(rs.getInt("user_id_id"));
        r.setSujet(rs.getString("sujet"));
        r.setDescription(rs.getString("description"));
        r.setEtat(rs.getString("etat"));
        r.setNom_client(rs.getString("nom_client"));
        reclamations.add(r);
    }

    return reclamations;
}
    public User getIduserByIdRec(int id){
        User u =new User();
        try {
            String query="SELECT * FROM `reclamation` where id="+id;
            Statement st=conx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setId(res.getInt("user_id_id"));
               
                
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        
        }
    
    public User getuserbyiduser(int id){
        User u =new User();
        try {
            String query="SELECT email FROM `User` where id="+id;
            Statement st=conx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setEmail(res.getString("email"));
                
               
                
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        
        }
       public int nbEncours(){
     String req="SELECT COUNT(*) FROM reclamation WHERE etat='en cours' ";
      
      int nb =0;
        
        try {
        	Statement stm = MaConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     
     
     public int nbTraité(){
     String req="SELECT COUNT(*) FROM reclamation WHERE etat='traité' ";
      
      int nb =0;
        
        try {
        	Statement stm = MaConnection.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return nb;
    }

} 
    

