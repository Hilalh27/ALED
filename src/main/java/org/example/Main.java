package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*Demandeur demandeur1 = new Demandeur("Hamdan", "Hilal", "rue Marie-Louise", "hilalh27@gmail.com", "1234a");
        Benevole benevole1 = new Benevole("Caillet", "Noé", "avenue Lamartine","caillet.noe@laposte.net", "1234a");

        Valideur valideur1 = new Valideur("Loubejac-Combalbert", "Jean-Philippe", "rue de vazerac", "jploubej@vazerac.earth", "1234a");
        valideur1.ajouterDemandeur(demandeur1);

        Mission mission1 = new Mission("transport objet", "Je veux récupérer mon shampooing chez Carrefour svp", 1800, new java.util.Date());
        demandeur1.ajouterMission(mission1);
        benevole1.accepterMission(mission1);
        benevole1.realiserMission(mission1);
        benevole1.ajouterAvis(new Avis(4,"pas assez rapide"));

        Mission mission2 = new Mission("transport objet", "Je veux récupérer de la beuh", 3600, new java.util.Date());
        demandeur1.ajouterMission(mission2);
        valideur1.invaliderMission(mission2);
        benevole1.accepterMission(mission2);
        benevole1.realiserMission(mission2);
        benevole1.ajouterAvis(new Avis(3,"pas de la bonne, quartier qui craint un peu"));*/

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utils.enregistrerNouvelUtilisateur("DUBIEN", "Mathis", 1, "mathisdubien@unilim.fr", "30 Place Denis Dussoubs", "abcd1", utilisateurDAO);


    }
}