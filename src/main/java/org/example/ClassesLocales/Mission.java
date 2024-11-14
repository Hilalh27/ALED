package org.example.ClassesLocales;
import java.sql.Date;

public class Mission {

    //ATTRIBUTS
    private int id_mission;
    private String statut;
    private String description;
    private int id_demandeur;
    private int id_benevole;
    private int id_valideur;
    private String motif_refus;
    private java.sql.Date date;

    //CONSTRUCTEUR

    public Mission(int id_mission, String statut, String description, int id_demandeur, int id_benevole, int id_valideur, String motif_refus, Date date) {
        this.id_mission = id_mission;
        this.statut = statut;
        this.description = description;
        this.id_demandeur = id_demandeur;
        this.id_benevole = id_benevole;
        this.id_valideur = id_valideur;
        this.motif_refus = motif_refus;
        this.date = date;
    }

    //METHODES


    public int getId_mission() {
        return id_mission;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }

    public String getDescription() {
        return description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_demandeur() {
        return id_demandeur;
    }

    public void setId_demandeur(int id_demandeur) {
        this.id_demandeur = id_demandeur;
    }

    public int getId_benevole() {
        return id_benevole;
    }

    public void setId_benevole(int id_benevole) {
        this.id_benevole = id_benevole;
    }

    public int getId_valideur() {
        return id_valideur;
    }

    public void setId_valideur(int id_valideur) {
        this.id_valideur = id_valideur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif_refus() {
        return motif_refus;
    }

    public void setMotif_refus(String motif_refus) {
        this.motif_refus = motif_refus;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id_mission=" + id_mission +
                ", statut='" + statut + '\'' +
                ", description='" + description + '\'' +
                ", id_demandeur=" + id_demandeur +
                ", id_benevole=" + id_benevole +
                ", id_valideur=" + id_valideur +
                ", motif_refus='" + motif_refus + '\'' +
                ", date=" + date +
                '}';
    }
}
