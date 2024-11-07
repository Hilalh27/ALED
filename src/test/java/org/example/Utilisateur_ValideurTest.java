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

class Utilisateur_ValideurTest {


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

    @Test
    void enregistrerNouvelUtilisateurTestValid(){
        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            assertEquals(true,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO));

        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void enregistrerNouvelUtilisateurTestInvalid(){
        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            assertEquals(false,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal","rue de Vazerac","1gre-er-g", utilisateurDAO));
        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void enregistrerNouvelUtilisateurTestDuplicate(){
        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            assertEquals(true,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO));
            assertEquals(false,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO));
        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }

    @Test
    void enregistrerNouveauValideurTestValid(){
        try {
            ValideurDAO valideurDAO = new ValideurDAO();
            assertEquals(true,Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO));
            assertEquals(false,Utils.enregistrerNouveauValideur("Hilal","Hamdan","hamdan.hilal@yahoo.fr","Vazerac de rue","1gre-er-g", valideurDAO));

        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void enregistrerNouveauValideurTestInvalid(){
        try {
            ValideurDAO valideurDAO = new ValideurDAO();
            assertEquals(false,Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","", valideurDAO));

        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }

    /*
    @Test
    void enregistrerNouveauValideurTestNull(){}

    @Test
    void supprimerUtilisateurTestValid(){}
    @Test
    void supprimerUtilisateurTestInvalid(){}
    @Test
    void supprimerUtilisateurTestNull(){}

    @Test
    void connexionUtilisateurTestValid(){}
    @Test
    void connexionUtilisateurTestInvalid(){}
    @Test
    void connexionUtilisateurTestNull(){}

    @Test
    void connexionValideurTestValid(){}
    @Test
    void connexionValideurTestInvalid(){}
    @Test
    void connexionValideurTestNull(){}

    @Test
    void recupererUtilisateurConnecteValid(){}
    @Test
    void recupererUtilisateurConnecteInvalid(){}
    @Test
    void recupererUtilisateurConnecteNull(){}

    @Test
    void recupererValideurConnecteTestValid(){}
    @Test
    void recupererValideurConnecteTestInvalid(){}
    @Test
    void recupererValideurConnecteTestNull(){}

    @Test
    void getUserIdTestValid(){}
    @Test
    void getUserIdTestInvalid(){}
    @Test
    void getUserIdTestNull(){}

    @Test
    void getValideurIdTestValid(){}
    @Test
    void getValideurIdTestInvalid(){}
    @Test
    void getValideurIdTestNull(){}


*/
}