package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemandeurTest {

    @Test
    void creerMission() {
        Demandeur dem = new Demandeur("DUBIEN", "Mathis", "30 Place Denis Dussoubs", "mathisdubien@unilim.fr", "abcd1");
        Mission mis = dem.creerMission("transport objet", "Je veux récupérer mon shampooing chez Carrefour svp", 1800, new java.util.Date());
        assertEquals(dem.getLastMission(),mis);
    }

    @Test
    void finirMission() {
        Demandeur dem = new Demandeur("DUBIEN", "Mathis", "30 Place Denis Dussoubs", "mathisdubien@unilim.fr", "abcd1");
        Mission mis = dem.creerMission("transport objet", "Je veux récupérer mon shampooing chez Carrefour svp", 1800, new java.util.Date());
        dem.finirMission(mis);
        assertEquals(dem.getLastMission(),null);
    }

}