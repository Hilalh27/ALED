package org.example;
import java.util.*;

public class Benevole extends Utilisateur {

    //ATRIBUTS
    private ArrayList<Avis> avis;
    private int moyenne;
    private ArrayList<Mission> missions;


    //CONSTRUCTEUR
    public Benevole(String nom, String prenom, String adresse, String email) {
        super(nom, prenom, adresse, email);
        this.avis = new ArrayList<Avis>();
        this.moyenne = 0;
        this.missions = new ArrayList<Mission>();
    }

    //METHODES
    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
        int moy = 0;
        for (int i=0; i<this.avis.size(); i++){
            moy += this.avis.get(i).getNote();
        }
        this.moyenne = moy/this.avis.size();
    }

    public int getMoyenne() {
        return this.moyenne;
    }

    public void afficherCommentaires(){
        for (int i=0; i<this.avis.size(); i++){
            System.out.println(this.avis.get(i).getCommentaire());
        }
    }

    public void accepterMission(Mission m) {
        if (m.getStatut().equals("validée") || m.getStatut().equals("en attente")){
            m.setStatut("acceptée");
        }
        else {
            System.out.println("Mission ne peut pas être acceptée, elle a été invalidée");
        }
    }

    public void realiserMission(Mission m) {
        if (m.getStatut().equals("acceptée")){
            m.setStatut(("réalisée"));
        }
        //this.missions.remove(this.missions.get(m));
    }
}
