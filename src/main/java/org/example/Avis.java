package org.example;

public class Avis {

    //ATTRIBUTS
    private int note;
    private String commentaire;

    //CONSTRUCTEUR
    public Avis(int note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }
}
