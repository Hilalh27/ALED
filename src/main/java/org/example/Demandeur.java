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
    }

    public Mission getLastMission(){
        if (missions.size() > 0){
            return missions.get(missions.size() - 1);
        }
        else{
            return null;
        }
    }
/*    //METHODES
    public Mission creerMission(String categorie, String description, int temps, java.sql.Date date){
        Mission mission = new Mission(categorie, description, temps, date);
        this.missions.add(mission);
        mission.setDemandeur(this);
        return mission;
    }

    public void finirMission(Mission mission){
        this.missions.remove(mission);
    }*/
}
