package org.example;
//import java.sql.SQLException;
import java.util.*;

public class Valideur {

    //ATTRIBUTS
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;

    //CONSTRUCTEUR
    public Valideur(String nom, String prenom, String adresse, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }

    //METHODES
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public void validerMission(Mission m) {
//        m.setStatut("validée");
//    }
//
//    public void invaliderMission(Mission m) {
//        m.setStatut("réalisée");
//    }
//
//    //valideur
//    public Mission getLastMissionAValider(){
//        if (missions_a_valider.size() > 0){
//            return missions_a_valider.get(missions_a_valider.size() - 1);
//        }
//        else{
//            return null;
//        }
//    }

}
