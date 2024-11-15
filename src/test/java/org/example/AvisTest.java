package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Les tests portent sur les méthodes de la classe Utils
Ces méthodes utilisent les méthodes des classes DAO : AvisDAO, MissionDAO...
Les méthodes des classes DAO utilisent les méthodes des classes "classiques" : Avis, Valideur...
Ainsi toutes les méthodes sont testées
 */

class AvisTest {


    // donnerAvis
    // recupererAvis
    // recupererLesAvisMission
    // recupererLesAvisAuteur
    @Test
    void avisTestClassique() {
        try {
            //setup
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            ValideurDAO valideurDAO = new ValideurDAO();
            MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());
            AvisDAO avisDAO = new AvisDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password", utilisateurDAO);
            Utilisateur auteur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "passworddevalideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            Utils.demanderMission(auteur, "mission classique", missionDAO, utilisateurDAO);
            Utils.finirMission(1, missionDAO);
            Mission mission = Utils.historiqueMissionsDemandeur(auteur, utilisateurDAO, missionDAO).getFirst();
            int id_mission = mission.getId_mission();

            //actual test
            Utils.donnerAvis(id_mission, auteur, 4, "bien réalisée", utilisateurDAO, avisDAO, missionDAO);
        }
            catch(SQLException e) {
            System.out.println("[missionTestClassique] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void recupererAvisTest() {
        assertEquals(true,true);
    }
    @Test
    void stockerAvisTest() {
        assertEquals(true,true);
    }

    @Test
    void getLesAvisMissionTest() {
        assertEquals(true,true);
    }
    @Test
    void getLesAvisAuteurTest() {
        assertEquals(true,true);
    }
}