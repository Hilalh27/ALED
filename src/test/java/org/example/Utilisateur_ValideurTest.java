package org.example;

import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
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
    void enregistrerNouvelUtilisateurTestValid(){
        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            System.out.println("Here 1");
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
            System.out.println("Here 2");
            assertEquals(true,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO));

            System.out.println("Here 3");
            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void enregistrerNouvelUtilisateurTestInvalid(){
        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            assertEquals(false,Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO));

            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
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

            //clean up
            Utils.supprimerUtilisateur("noe@yahoo.fr", "1gre-er-g", utilisateurDAO);
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

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void enregistrerNouveauValideurTestInvalid(){ //no password
        try {
            ValideurDAO valideurDAO = new ValideurDAO();
            assertEquals(false,Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","", valideurDAO));

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch (SQLException e){
            System.out.println("[enregistrerNouvelUtilisateurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void supprimerUtilisateurTestValid(){
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals(true,Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr","1gre-er-g", utilisateurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void supprimerUtilisateurTestNotFound(){ //wrong email
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals(false,Utils.supprimerUtilisateur("hilal@yahoo.fr","1gre-er-g", utilisateurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerUtilisateurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void supprimerUtilisateurTestTwice(){
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals(true,Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr","1gre-er-g", utilisateurDAO));
            assertEquals(false,Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr","1gre-er-g", utilisateurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerUtilisateurTestTwice] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void supprimerValideurTestValid(){
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals(true,Utils.supprimerValideur("hamdan.hilal@yahoo","1gre-er-g", valideurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerValideurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void supprimerValideurTestNotFound(){ //wrong email
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals(false,Utils.supprimerValideur("hilal@yahoo.fr","1gre-er-g", valideurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerValideurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void supprimerValideurTestTwice(){
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);

            assertEquals(true,Utils.supprimerValideur("al@yahoo.fr","1gre-er-g", valideurDAO));
            assertEquals(false,Utils.supprimerValideur("al@yahoo.fr","1gre-er-g", valideurDAO));
        }
        catch(SQLException e){
            System.out.println("[supprimerValideurTestTwice] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void connexionUtilisateurTestValid(){
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals(true,Utils.connexionUtilisateur("hamdan.hilal@yahoo.fr","1gre-er-g"));

            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
        }
        catch(SQLException e){
            System.out.println("[connexionUtilisateurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void connexionUtilisateurTestInvalid(){ //wrong password
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals(true,Utils.connexionUtilisateur("hamdan.hilal@yahoo.fr","wrongpassword"));

            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
        }
        catch(SQLException e){
            System.out.println("[connexionUtilisateurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void connexionValideurTestValid(){
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals(true,Utils.connexionValideur("hamdan.hilal@yahoo.fr","1gre-er-g"));

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch(SQLException e){
            System.out.println("[connexionValideurTestValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void connexionValideurTestInvalid(){ //wrong password
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals(true,Utils.connexionValideur("hamdan.hilal@yahoo.fr","wrongpassword"));

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch(SQLException e){
            System.out.println("[connexionValideurTestInvalid] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void recupererUtilisateurConnecteValid(){
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals("hamdan.hilal@yahoo.fr",Utils.recupererUtilisateurConnecte("hamdan.hilal@yahoo.fr",utilisateurDAO).getEmail());

            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
        }
        catch(SQLException e){
            System.out.println("[recupererUtilisateurConnecteValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void recupererUtilisateurConnecteInvalid(){ //wrong email
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            assertEquals("hamdan.hilal@yahoo.fr",Utils.recupererUtilisateurConnecte("hoo@fr",utilisateurDAO).getEmail());

            //clean up
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr", "1gre-er-g", utilisateurDAO);
        }
        catch(SQLException e){
            System.out.println("[recupererUtilisateurConnecteInvalid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void recupererUtilisateurConnecteSuppressed(){
        try{
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utils.enregistrerNouvelUtilisateur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", utilisateurDAO);
            Utils.supprimerUtilisateur("hamdan.hilal@yahoo.fr","1gre-er-g", utilisateurDAO);
            assertEquals(null,Utils.recupererUtilisateurConnecte("hoo@fr",utilisateurDAO));
        }
        catch(SQLException e){
            System.out.println("[recupererUtilisateurConnecteSuppressed] failed because of SQLException: " + e.getMessage());
        }
    }


    @Test
    void recupererValideurConnecteValid(){
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals("hamdan.hilal@yahoo.fr",Utils.recupererValideurConnecte("hamdan.hilal@yahoo.fr",valideurDAO).getEmail());

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch(SQLException e){
            System.out.println("[recupererValideurConnecteValid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void recupererValideurConnecteInvalid(){ //wrong email
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            assertEquals("hamdan.hilal@yahoo.fr",Utils.recupererValideurConnecte("hoo@fr", valideurDAO).getEmail());

            //clean up
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr", "1gre-er-g", valideurDAO);
        }
        catch(SQLException e){
            System.out.println("[recupererValideurConnecteInvalid] failed because of SQLException: " + e.getMessage());
        }
    }
    @Test
    void recupererValideurConnecteSuppressed(){
        try{
            ValideurDAO valideurDAO = new ValideurDAO();
            Utils.enregistrerNouveauValideur("Hamdan","Hilal","hamdan.hilal@yahoo.fr","rue de Vazerac","1gre-er-g", valideurDAO);
            Utils.supprimerValideur("hamdan.hilal@yahoo.fr","1gre-er-g", valideurDAO);
            assertEquals(null,Utils.recupererValideurConnecte("hoo@fr",valideurDAO));
        }
        catch(SQLException e){
            System.out.println("[recupererValideurConnecteSuppressed] failed because of SQLException: " + e.getMessage());
        }
    }

}