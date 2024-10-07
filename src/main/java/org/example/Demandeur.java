package org.example;
import java.util.*;

public class Demandeur extends Utilisateur{

    //ATTRIBUTS
    private Valideur valideur;
    private ArrayList<Mission> missions;


    //CONSTRUCTEUR
    public Demandeur(String nom, String prenom, String adresse, String email) {
        super(nom, prenom, adresse, email);
        this.valideur = null;
        this.missions = new ArrayList<Mission>();
    }


    //METHODES
    public void ajouterMission(Mission mission){
        this.missions.add(mission);
    }

    public void finirMission(Mission mission){
        this.missions.remove(mission);
    }
}
