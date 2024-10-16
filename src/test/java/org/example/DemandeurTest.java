package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemandeurTest {

    @Test
    void ajouterMission() {
        Demandeur dem = new Demandeur("DUBIEN", "Mathis", "30 Place Denis Dussoubs", "mathisdubien@unilim.fr", "abcd1");
        Mission mis = new Mission("transport objet", "Je veux récupérer mon shampooing chez Carrefour svp", 1800, new java.util.Date());
        dem.ajouterMission(mis);
        assertEquals(dem.mi)
    }

    @Test
    void finirMission() {
        assertEquals(2,3);
    }
}