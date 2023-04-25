/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;

import com.sportsverse.entities.Reclamation;
import com.sportsverse.entities.Reponse;
import com.sportsverse.entities.User;
import com.sportsverse.tools.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class UserService implements IUser<User>{

Statement stm; 
    Connection conx;
    
    public UserService(){
         conx = MaConnection.getInstance().getCnx();
    }
    @Override
    public List<User> afficheListeR() throws SQLException {
    return null;
         
    }

    

    @Override
    public void modifierO(User o,int id) throws SQLException {
         
    }
      public void supprimer(int id) {
   
      
      
        }
      
      public User getIduserByIdRec(int id){
        User u =new User();
        try {
            String query="SELECT * FROM `reclamation` where id="+id;
            Statement st=conx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setId(res.getInt("id_user"));
               
                
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
            String query="SELECT * FROM `User` where id="+id;
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

    @Override
    public void ajouterR(User r) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

