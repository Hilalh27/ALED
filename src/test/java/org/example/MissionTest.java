package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissionTest {

    @Test
    void setDemandeur() {
        Demandeur dem = new Demandeur("DUBIEN", "Mathis", "30 Place Denis Dussoubs", "mathisdubien@unilim.fr", "abcd1");
        dem.creerMission("transport objet", "Je veux récupérer mon shampooing chez Carrefour svp", 1800, new java.util.Date());
        assertEquals(dem.getLastMission().getDemandeur(),dem);
    }
}