package org.example;

import java.sql.SQLException;

abstract public class Utilisateur {

    //ATTRIBUTS
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;

    //CONSTRUCTEUR
    public Utilisateur(String nom, String prenom, String adresse, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email; // s'assurer du bon format
        this.password = password; //S'assurer qu'il y a des chiffres et des lettres...
    }

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
}