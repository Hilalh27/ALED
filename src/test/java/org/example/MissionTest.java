package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            ValideurDAO valideurDAO = new ValideurDAO();
            MissionDAO missionDAO = new MissionDAO(UtilisateurDAO.getConnectionUtilisateurDAO());

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "passworddevalideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);


            Utils.demanderMission(demandeur, "mission classique", missionDAO, utilisateurDAO);
            assertEquals("mission classique", Utils.avoirToutesMissionsEnAttente(missionDAO).getFirst().getDescription());
            Utils.validerMission(valideur, 1, missionDAO, valideurDAO);
            assertEquals("validée", Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).getFirst().getStatut());
            assertEquals("mission classique", Utils.avoirToutesMissionsValidees(missionDAO).getFirst().getDescription());
            Utils.accepterMissionDemandee(benevole, 1, missionDAO, utilisateurDAO);
            assertEquals("acceptée", Utils.missionsEnCoursBenevole(benevole, utilisateurDAO, missionDAO).getFirst().getStatut());
            Utils.finirMission(1, missionDAO);
            assertEquals("réalisée", Utils.historiqueMissionsBenevole(benevole, utilisateurDAO, missionDAO).getFirst().getStatut());

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

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "passworddevalideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            //Tests
            Utils.demanderMission(demandeur, "description de la mission", missionDAO, utilisateurDAO);
            assertEquals("description de la mission", Utils.avoirToutesMissionsEnAttente(missionDAO).getFirst().getDescription());
            Utils.modifierDescriptionMission(1, "nouvelle description", missionDAO);
            assertEquals("nouvelle description", Utils.avoirToutesMissionsEnAttente(missionDAO).getFirst().getDescription());
            Utils.refuserMission(valideur, 1, "motif du refus", missionDAO, valideurDAO);
            assertEquals("refusée", Utils.historiqueMissionsDemandeur(demandeur, utilisateurDAO, missionDAO).getFirst().getStatut());
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

            Utils.enregistrerNouvelUtilisateur("nom", "prenom", "email@yahoo.fr", "adresse", "password", utilisateurDAO);
            Utilisateur demandeur = Utils.recupererUtilisateurConnecte("email@yahoo.fr", utilisateurDAO);
            Utils.enregistrerNouvelUtilisateur("bla","bla","bla@gmail.com", "adressebla","pwd", utilisateurDAO);
            Utilisateur benevole = Utils.recupererUtilisateurConnecte("bla@gmail.com", utilisateurDAO);
            Utils.enregistrerNouveauValideur("valideur", "valideur", "email@valideur.fr", "adresse_valideur", "passworddevalideur",valideurDAO);
            Valideur valideur = Utils.recupererValideurConnecte("email@valideur.fr", valideurDAO);

            //Tests
            Utils.proposerMission(benevole, "spontanee la mission", missionDAO, utilisateurDAO);
            assertEquals("spontanee la mission", Utils.avoirToutesMissionsEnAttente(missionDAO).getFirst().getDescription());
            Utils.validerMission(valideur, 1, missionDAO, valideurDAO);
            assertEquals("validée", Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).getFirst().getStatut());
            assertEquals("spontanee la mission", Utils.avoirToutesMissionsSpontaneesValidees(missionDAO).getFirst().getDescription());
            Utils.accepterMissionSpontanee(demandeur, 1, missionDAO, utilisateurDAO);
            assertEquals("acceptée", Utils.missionsEnCoursDemandeur(demandeur, utilisateurDAO, missionDAO).getFirst().getStatut());
            Utils.finirMission(1, missionDAO);
            assertEquals("réalisée", Utils.historiqueMissionsBenevole(benevole, utilisateurDAO, missionDAO).getFirst().getStatut());
        }
        catch (SQLException e) {
            System.out.println("[missionTestSpontanee] failed because of SQLException: " + e.getMessage());
        }
    }

}