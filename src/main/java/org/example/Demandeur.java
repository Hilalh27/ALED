package org.example;
import java.sql.SQLException;
import java.util.*;

public class Demandeur extends Utilisateur{

    //ATTRIBUTS
    private Valideur valideur;
    private ArrayList<Mission> missions;


    //CONSTRUCTEUR
    public Demandeur(String nom, String prenom, String adresse, String email, String password) {
        super(nom, prenom, adresse, email, password);
        this.valideur = null;
        this.missions = new ArrayList<Mission>();
        try {
            UtilisateurDAO utilDAO = new UtilisateurDAO();
            utilDAO.ajouterUtilisateur(this, "demandeur");
        }
        catch (SQLException e) {
            System.out.println("ERREUR : impossible de créer un utilisateur dans la BDD");
        }
    }


    //METHODES
    public void ajouterMission(Mission mission){
        this.missions.add(mission);
    }

    public void finirMission(Mission mission){
        this.missions.remove(mission);
    }
}
