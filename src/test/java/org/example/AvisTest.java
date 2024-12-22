package org.example;

import org.example.BDDCommunication.*;
import org.example.ClassesLocales.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password1", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd1", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "password2valideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            Utils.demanderMission(demandeur, "mission classique", missionDAO, utilisateurDAO);
            Mission mission = Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).get(0);
            int id_mission = mission.getId_mission();
            Utils.validerMission(valideur, id_mission, missionDAO, valideurDAO);
            Utils.accepterMissionDemandee(benevole, id_mission, missionDAO, utilisateurDAO);
            Utils.finirMission(id_mission, missionDAO);

            //actual test
            Utils.donnerAvis(id_mission, demandeur, 4, "bien réalisée", utilisateurDAO, avisDAO, missionDAO);
            Utils.donnerAvis(id_mission, benevole, 3, "trop chronophage", utilisateurDAO, avisDAO, missionDAO);
            assertEquals("bien réalisée",Utils.recupererAvis(id_mission, "email@yahoo.fr", avisDAO, utilisateurDAO).getCommentaire());
            assertEquals("bien réalisée", Utils.recupererLesAvisMission(id_mission, avisDAO).get(0).getCommentaire());
            List<Avis> avisMission = Utils.recupererLesAvisMission(id_mission, avisDAO);
            assertEquals("trop chronophage", avisMission.get(avisMission.size()-1).getCommentaire());
            assertEquals("trop chronophage", Utils.recupererLesAvisAuteur("bla@gmail.com", avisDAO, utilisateurDAO).get(0).getCommentaire());

            //cleanup
            Utils.supprimerAvis(id_mission, Utils.getUserId(demandeur, utilisateurDAO), avisDAO);
            Utils.supprimerAvis(id_mission, Utils.getUserId(benevole, utilisateurDAO), avisDAO);
            Utils.supprimerUtilisateur("email@yahoo.fr","password1", utilisateurDAO);
            Utils.supprimerUtilisateur("bla@gmail.com","pwd1", utilisateurDAO);
            Utils.supprimerValideur("email@valideur.fr","password2valideur", valideurDAO);
            Utils.supprimerMission("mission classique", missionDAO);
        }
            catch(SQLException e) {
            System.out.println("[missionTestClassique] failed because of SQLException: " + e.getMessage());
        }
    }
}