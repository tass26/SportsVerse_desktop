/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tools.MyConnection;
import java.sql.Connection;

/**
 *
 * @author Chaima
 */
public class CRUDUser {

    public void SignUp(User P) {
        try {

            String requete = "INSERT INTO User(Firstname,lastname,Phone,Email,Addresse,mdp,Speciality,Role)" + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, P.getFirstname());
            pst.setString(2, P.getLastname());
            pst.setInt(3, P.getPhone());
            pst.setString(4, P.getEmail());
            pst.setString(5, P.getAdresse());
            pst.setString(6, User.hash(P.getMdp()));
            pst.setString(7, P.getSpeciality());
            pst.setString(8, P.getRole());
            pst.executeUpdate();

            System.out.println("Patient ajouté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String checkRole(int Phone) {
        String default_return = "ND";
        try {
            String req = "select Role from user where Phone=?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, Phone);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals("Patient")) {

                    return "Patient";
                } else if (rs.getString(1).equals("Medecin")) {
                    return "Medecin";
                } else if (rs.getString(1).equals("Biologiste")) {
                    return "Biologiste";
                } else if (rs.getString(1).equals("Pharmacien")) {
                    return "Pharmacien";
                } else if (rs.getString(1).equals("Biologiste")) {
                    return "Biologiste";
                } else if (rs.getString(1).equals("Admin")) {
                    return "Admin";
                } else if (rs.getString(1).equals("ProfSante")) {
                    return "ProfSante";
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return default_return;
    }

   

    public boolean chercherPatientInt(int Phone, String mdp) {
        boolean test = false;
        String storedPassword = "";
        try {
            String requete = "SELECT * FROM user WHERE Phone = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, Phone);

            ResultSet rs = pst.executeQuery();

            //  while (rs.next()) {
            // String storedPassword = null;
            if (rs.next()) {
                storedPassword = rs.getString("mdp");
                User u = this.findById(rs.getInt("idUser"));
         
                System.out.println(storedPassword);
            }
            String hashedInputPassword = User.hash(mdp);
            // System.out.println(hashedInputPassword);
            if (hashedInputPassword.equals(storedPassword)) {
                // mot de passe correct, l'utilisateur est authentifié

                test = true;
                System.out.println(test);
            } else {
                System.out.println(test);
                // mot de passe incorrect, l'utilisateur n'est pas authentifié
                return test;

            }

            // test=true ; 
        } catch (SQLException ex) {
            // System.out.println("fin"); 
            System.err.println(ex.getMessage());
        }
        return test;
    }

    public boolean isPhoneNumberUnique(int num) throws ClassNotFoundException, SQLException {
        try {
            String requete = "SELECT * FROM user WHERE Phone= ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User findById(int idconnected) {
        User p = null;
        try {
            String req = "select  * from user where idUser=? ";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(req);
            pst.setInt(1, idconnected);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = new User(rs.getInt("idUser"),rs.getString("Firstname"), rs.getString("lastname"), rs.getInt("Phone"), rs.getString("Email"), rs.getString("Addresse"),rs.getString("Role"), rs.getString("mdp"),rs.getString("speciality") );
            }
        } catch (SQLException a) {
            System.out.println(a.getMessage());
        }
        return p;
    }

    //CRUD USER 
    public void addUser(User P) {
        try {
            String requete = "INSERT INTO users (Firstname,lastname,Phone,Email,Addresse,mdp,Role)" + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, P.getFirstname());
            pst.setString(2, P.getLastname());
            pst.setInt(3, P.getPhone());
            pst.setString(4, P.getEmail());
            pst.setString(5, P.getAdresse());
            pst.setString(6, P.getMdp());
            pst.setString(7, P.getRole());
            pst.executeUpdate();

            System.out.println("Utilisateur  ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<User> showUser() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM users";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //  list=(ObservableList<User>)rs ; 

                //  rs.toString() ; 
                // ObservableList<User> list = getList(rs);
                User u = new User(rs.getString("Firstname"), rs.getString("lastname"), rs.getInt("Phone"), rs.getString("Email"), rs.getString("Addresse"), rs.getString("mdp"), rs.getString("Role"));

                list.add(u);

                // ObservableList <User> list = getList(rs);
                //  list.add(new User(rs.getString("Nom"),rs.getInt("Phone"),rs.getString("Email"),rs.getString("Adresse"),rs.getString("Role"),rs.getString("mdp"))); 
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void editUser(User P, int Phone) {
        try {

            String requete = "UPDATE user SET Firstname= ? ,lastname=?,Email = ? ,Addresse= ? ,mdp= ? WHERE Phone= ? ";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, P.getFirstname());
            pst.setString(2, P.getLastname());
            pst.setString(3, P.getEmail());
            pst.setString(4, P.getAdresse());
            pst.setString(5, P.getMdp());
            pst.setInt(6, Phone);
            //    pst.setString(6, P.getMdp());

            pst.executeUpdate();

            System.out.println("Utilisateur modifié!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteUser(int Phone) {

        try {
            String requete = "DELETE FROM users WHERE Phone = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, Phone);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean phoneExists(int phone) {
        String query = "SELECT * FROM users WHERE Phone = ?";
        try (PreparedStatement stmt = new MyConnection().cn.prepareStatement(query)) {
            stmt.setInt(1, phone);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Error checking if phone exists: " + ex.getMessage());
            return false;
        }
    }
    
}
