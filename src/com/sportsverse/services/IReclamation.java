/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.services;
import com.sportsverse.entities.Reclamation;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ASUS
 */
public interface IReclamation<T> {
 List<T> afficheListeR() throws SQLException;
    void ajouterR(T r)throws SQLException;  
    void modifierO(T o,int id)throws SQLException;

}
