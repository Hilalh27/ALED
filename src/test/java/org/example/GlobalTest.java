package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Les tests portent sur les méthodes de la classe Utils
Ces méthodes utilisent les méthodes des classes DAO : AvisDAO, MissionDAO...
Les méthodes des classes DAO utilisent les méthodes des classes "classiques" : Avis, Valideur...
Ainsi toutes les méthodes sont testées
 */

class GlobalTest {

    @Test
    void emailValidTestValid() {
        String emailValid = "noe.caillet@laposte.net";
        assertEquals(true, Utils.emailValide(emailValid));
    }
    @Test
    void emailValidTestInvalid() {
        String emailInvalid = "email invalide";
        assertEquals(false, Utils.emailValide(emailInvalid));
    }
    @Test
    void emailValidTestNull() {
        String emailNull = "";
        assertEquals(false, Utils.emailValide(emailNull));
    }

    @Test
    void passwordValideTestValid() {
        String passwordValid = "1aergergerg";
        assertEquals(true, Utils.passwordValide(passwordValid));
    }
    @Test
    void passwordValideTestInvalid() {
        String passwordInvalid = "---";
        assertEquals(false, Utils.passwordValide(passwordInvalid));
    }
    @Test
    void passwordValideTestNull() {
        String passwordNull = "";
        assertEquals(false, Utils.passwordValide(passwordNull));
    }

}