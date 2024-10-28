package org.example;

import java.sql.Date;

public class Avis {

    //ATTRIBUTS
    int id_mission;
    int id_utilisateur_auteur;
    private int note;
    private String commentaire;
    private java.sql.Date date_avis;

    //CONSTRUCTEUR
    public Avis(int id_mission, int id_utilisateur_auteur, int note, String commentaire, java.sql.Date date_avis) {
        this.id_mission = id_mission;
        this.id_utilisateur_auteur = id_utilisateur_auteur;
        this.note = note;
        this.commentaire = commentaire;
        this.date_avis = date_avis;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getId_mission() {
        return id_mission;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }

    public int getId_utilisateur_auteur() {
        return id_utilisateur_auteur;
    }

    public void setId_utilisateur_auteur(int id_utilisateur_auteur) {
        this.id_utilisateur_auteur = id_utilisateur_auteur;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(Date date_avis) {
        this.date_avis = date_avis;
    }
}
