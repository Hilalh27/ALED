package org.example;
import java.util.*;

public class Valideur extends Utilisateur {

    //ATTRIBUTS
    private ArrayList<Demandeur> demandeurs;

    //CONSTRUCTEURS
    public Valideur(String nom, String prenom, String adresse, String email) {
        super(nom, prenom, adresse, email);
        this.demandeurs = new ArrayList<Demandeur>();
    }
    public Valideur(String nom, String prenom, String adresse, String email, Demandeur d) {
        super(nom, prenom, adresse, email);
        this.demandeurs.add(d);
    }

    //METHODES
    public void ajouterDemandeur(Demandeur d) {
        this.demandeurs.add(d);
    }

    public void validerMission(Mission m) {
        m.setStatut("validée");
    }

    public void invaliderMission(Mission m) {
        m.setStatut("réalisée");
    }
}
