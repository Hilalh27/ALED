package org.example;

import org.example.BDDCommunication.MissionDAO;
import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.BDDCommunication.AvisDAO;
import org.example.ClassesLocales.Mission;
import org.example.ClassesLocales.Utilisateur;
import org.example.ClassesLocales.Valideur;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Les tests portent sur les méthodes de la classe Utils
Ces méthodes utilisent les méthodes des classes DAO : AvisDAO, MissionDAO...
Les méthodes des classes DAO utilisent les méthodes des classes "classiques" : Avis, Valideur...
Ainsi toutes les méthodes sont testées
 */

class MissionTest {

    //scénarios de tests
    /*
    -> plusieurs missions
    -> plusieurs bénévoles pour une mission
     */


    // Demandeur crée une mission
    // Valideur la valide
    // Benevole l'accepte
    // Benevole la finit
    @Test
    void missionTestClassique() {
        try{
            //Setup
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            ValideurDAO valideurDAO = new ValideurDAO();
            MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password1", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd1", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "password2valideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            //Test
            Utils.demanderMission(demandeur, "mission classique", missionDAO, utilisateurDAO);
            int id_mission = Utils.getIdMission("mission classique", missionDAO);
            assertEquals("mission classique", Utils.avoirToutesMissionsEnAttente(missionDAO).getLast().getDescription());
            Utils.validerMission(valideur, id_mission, missionDAO, valideurDAO);
            assertEquals("validée", Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).getLast().getStatut());
            assertEquals("mission classique", Utils.avoirToutesMissionsValidees(missionDAO).getLast().getDescription());
            Utils.accepterMissionDemandee(benevole, id_mission, missionDAO, utilisateurDAO);
            assertEquals("acceptée", Utils.missionsEnCoursBenevole(benevole, utilisateurDAO, missionDAO).getLast().getStatut());
            Utils.finirMission(id_mission, missionDAO);
            assertEquals("réalisée", Utils.historiqueMissionsBenevole(benevole, utilisateurDAO, missionDAO).getLast().getStatut());

            // clean up
            Utils.supprimerUtilisateur("email@yahoo.fr","password1", utilisateurDAO);
            Utils.supprimerUtilisateur("bla@gmail.com","pwd1", utilisateurDAO);
            Utils.supprimerValideur("email@valideur.fr","password2valideur", valideurDAO);
            Utils.supprimerMission("mission classique", missionDAO);

        }
        catch(SQLException e) {
            System.out.println("[missionTestClassique] failed because of SQLException: " + e.getMessage());
        }
    }



    // Demandeur crée une mission
    // Modification de la description de la mission
    // Valideur la refuse
    @Test
    void missionTestRefus() {
        try {
            //Setup
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            ValideurDAO valideurDAO = new ValideurDAO();
            MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password1", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "password2valideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            //Tests
            Utils.demanderMission(demandeur, "description de la mission", missionDAO, utilisateurDAO);
            int id_mission = Utils.getIdMission("description de la mission", missionDAO);
            assertEquals("description de la mission", Utils.avoirToutesMissionsEnAttente(missionDAO).getLast().getDescription());
            Utils.modifierDescriptionMission(id_mission, "nouvelle description", missionDAO);
            assertEquals("nouvelle description", Utils.avoirToutesMissionsEnAttente(missionDAO).getLast().getDescription());
            Utils.refuserMission(valideur, id_mission, "motif du refus", missionDAO, valideurDAO);
            assertEquals("refusée", Utils.historiqueMissionsDemandeur(demandeur, utilisateurDAO, missionDAO).getLast().getStatut());

            //Cleanup
            Utils.supprimerUtilisateur("email@yahoo.fr","password1", utilisateurDAO);
            Utils.supprimerValideur("email@valideur.fr","password2valideur", valideurDAO);
            Utils.supprimerMission("nouvelle description", missionDAO);
        }
        catch (SQLException e) {
            System.out.println("[missionTestRefus] failed because of SQLException: " + e.getMessage());
        }
    }


    // Demandeur propose une mission spontanée
    // Valideur la valide
    // Demandeur l'accepte
    // Benevole la finit
    @Test
    void missionTestSpontanee() {
        try {
            //Setup
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            ValideurDAO valideurDAO = new ValideurDAO();
            MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password1", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd1", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "password2valideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            //Tests
            Utils.proposerMission(benevole, "spontanee la mission", missionDAO, utilisateurDAO);
            int id_mission = Utils.getIdMission("spontanee la mission", missionDAO);
            assertEquals("spontanee la mission", Utils.avoirToutesMissionsEnAttente(missionDAO).getLast().getDescription());
            Utils.validerMission(valideur, id_mission, missionDAO, valideurDAO);
            List<Mission> missionsEnCoursBenevole = Utils.missionsEnCoursBenevole(benevole, utilisateurDAO, missionDAO);
            assertEquals("validée", missionsEnCoursBenevole.getLast().getStatut());
            assertEquals("spontanee la mission", Utils.avoirToutesMissionsSpontaneesValidees(missionDAO).getLast().getDescription());
            Utils.accepterMissionSpontanee(demandeur, id_mission, missionDAO, utilisateurDAO);
            assertEquals("acceptée", Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).getLast().getStatut());
            Utils.finirMission(id_mission, missionDAO);
            assertEquals("réalisée", Utils.historiqueMissionsBenevole(benevole, utilisateurDAO, missionDAO).getLast().getStatut());

            //Cleanup
            Utils.supprimerUtilisateur("email@yahoo.fr","password1", utilisateurDAO);
            Utils.supprimerUtilisateur("bla@gmail.com","pwd1", utilisateurDAO);
            Utils.supprimerValideur("email@valideur.fr","password2valideur", valideurDAO);
            Utils.supprimerMission("spontanee la mission", missionDAO);
        }
        catch (SQLException e) {
            System.out.println("[missionTestSpontanee] failed because of SQLException: " + e.getMessage());
        }
        catch (NoSuchElementException e){
            System.out.println("[missionTestSpontanee] failed because of NoSuchElementException: " + e.getMessage());
        }
    }

}
