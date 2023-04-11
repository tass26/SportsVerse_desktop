package Entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class User {
    private int idUser;
    private String Firstname ; 
    private String lastname;
    private int Phone;
    private String Email;
    private String Adresse;
    private String Role;
    private String mdp;
    private String speciality;

    public User(String Role) {
        
        this.Role = Role;
    }

    public User(int idUser, String Firstname, String lastname, int Phone, String Email, String Adresse, String Role, String mdp, String speciality) {
        this.idUser = idUser;
        this.Firstname = Firstname;
        this.lastname = lastname;
        this.Phone = Phone;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Role = Role;
        this.mdp = mdp;
        this.speciality = speciality;
    }
        public User(String Firstname, String lastname, int Phone, String Email, String Adresse, String Role, String mdp) {
        this.Firstname = Firstname;
        this.lastname = lastname;
        this.Phone = Phone;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Role = Role;
        this.mdp = mdp;
       
    }








    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public User(int Phone, String Email, String Adresse, String Role, String mdp) {
        this.Phone = Phone;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Role = Role;
        this.mdp = mdp;
    }

    public User(int Phone, String Role) {
        this.Phone = Phone;
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", Firstname=" + Firstname + ", lastname=" + lastname + ", Phone=" + Phone + ", Email=" + Email + ", Adresse=" + Adresse + ", Role=" + Role + ", mdp=" + mdp + ", speciality=" + speciality + '}';
    }

public User(String Firstname, String lastname, int Phone, String Adresse,String speciality) {
        this.Firstname = Firstname;
        this.lastname = lastname;
        this.Phone = Phone;
        this.Adresse = Adresse;
        this.speciality = speciality;
}


    public static String hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("SHA-256 algorithm not found");
            return null;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

   }