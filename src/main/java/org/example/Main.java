package org.example;

public class Main {
    public static void main(String[] args) {

        Demandeur demandeur1 = new Demandeur("Hamdan", "Hilal", "rue Marie-Louise", "hilalh27@gmail.com");

        Benevole benevole1 = new Benevole("Caillet", "Noé", "avenue Lamartine","caillet.noe@laposte.net");

        Valideur valideur1 = new Valideur("Loubejac-Combalbert", "Jean-Philippe", "rue de vazerac", "jploubej@vazerac.earth");
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
        benevole1.ajouterAvis(new Avis(3,"pas assez rapide"));
    }
}