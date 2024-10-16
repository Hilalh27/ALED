package org.example;
import java.util.*;

public class Mission {

    //ATTRIBUTS
    private String statut;
    private String categorie;
    private String description;
    private int temps;
    private Date date;
    private Demandeur demandeur;


    //CONSTRUCTEUR
    public Mission(String categorie, String description, int temps, Date date) {
        this.statut = "en attente";
        this.categorie = categorie;
        this.description = description;
        this.temps = temps;
        this.date = date;
        this.demandeur = null;
    }

    public void setDemandeur(Demandeur demandeur){
        this.demandeur = demandeur;
    }

    public Demandeur getDemandeur(){
        return this.demandeur;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

    @Override
    public String toString() {
        return "Mission: " + description + ", Statut: " + statut + ", Date: " + date.toString();
    }

}
