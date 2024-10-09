package org.example;

import java.sql.SQLException;

public class Utils {

    public static boolean emailValide(String email) {
        // Regex - expression régulière - pour valider l'email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    public static boolean passwordValide(String password) {
        // contient au moins une lettre et un chiffre
        return password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).+");
    }

    // Méthode pour obtenir le statut sous forme de string
    private static String getStatutString(int statut) {
        switch (statut) {
            case 1:
                return "demandeur";
            case 2:
                return "bénévole";
            case 3:
                return "valideur";
            default:
                throw new IllegalArgumentException("Statut inconnu: " + statut);
        }
    }

    private static int getStatutInt(String statut) {
        switch (statut.toLowerCase()) { // toLowerCase() pour maj et min
            case "demandeur":
                return 1;
            case "bénévole":
                return 2;
            case "valideur":
                return 3;
            default:
                throw new IllegalArgumentException("Statut inconnu: " + statut);
        }
    }


    public static boolean enregistrerNouvelUtilisateur(String nom, String prenom, int statut, String email, String adresse, String password, UtilisateurDAO utilisateurDAO) throws SQLException, SQLException {

        if (!emailValide(email)) {
            System.out.println("Email invalide.");
            return false;
        }

        if (!passwordValide(password)) {
            System.out.println("Mot de passe invalide. Il doit contenir au moins une lettre et un chiffre.");
            return false;
        }

        if (utilisateurDAO.emailExiste(email)) {
            System.out.println("L'email existe déjà.");
            return false;
        }

        Utilisateur nouvelUtilisateur;
        switch (statut) {
            case 1:
                nouvelUtilisateur = new Demandeur(nom, prenom, adresse, email, password);
                break;
            case 2:
                nouvelUtilisateur = new Benevole(nom, prenom, adresse, email, password);
                break;
            case 3:
                nouvelUtilisateur = new Valideur(nom, prenom, adresse, email, password);
                break;
            default:
                throw new IllegalArgumentException("Statut inconnu: " + statut);
        }
        utilisateurDAO.ajouterUtilisateur(nouvelUtilisateur, getStatutString(statut));
        System.out.println("Nouvel utilisateur enregistré avec succès.");
        return true;
    }

}