package org.example;
import java.util.*;

public class Mission {

    //ATTRIBUTS
    private String statut;
    private String categorie;
    private String description;
    private int temps;
    private Date date;


    //CONSTRUCTEUR
    public Mission(String categorie, String description, int temps, Date date) {
        this.statut = "en attente";
        this.categorie = categorie;
        this.description = description;
        this.temps = temps;
        this.date = date;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return statut;
    }
}
