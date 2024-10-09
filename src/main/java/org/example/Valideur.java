package org.example;
import java.util.*;

public class Valideur extends Utilisateur {

    //ATTRIBUTS
    private ArrayList<Demandeur> demandeurs;

    //CONSTRUCTEURS
    public Valideur(String nom, String prenom, String adresse, String email, String password) {
        super(nom, prenom, adresse, email, password);
        this.demandeurs = new ArrayList<Demandeur>();
    }
    /*
    public Valideur(String nom, String prenom, String adresse, String email, String password, Demandeur d) {
        super(nom, prenom, adresse, email, password);
        this.demandeurs.add(d);
    }
    Il n'ajoute pas les demandeurs forcement à la création, vaut mieux en faire une méthode
     */

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
