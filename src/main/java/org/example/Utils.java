package org.example;

import org.example.BDDCommunication.AvisDAO;
import org.example.BDDCommunication.MissionDAO;
import org.example.BDDCommunication.UtilisateurDAO;
import org.example.BDDCommunication.ValideurDAO;
import org.example.ClassesLocales.Avis;
import org.example.ClassesLocales.Mission;
import org.example.ClassesLocales.Utilisateur;
import org.example.ClassesLocales.Valideur;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Utils {

    // verifie qu'une adresse email est de la bonne forme
    public static boolean emailValide(String email) {
        // Regex - expression régulière - pour valider l'email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    // verifie qu'un mdp respecte les lettres
    public static boolean passwordValide(String password) {
        // contient au moins une lettre et un chiffre
        return password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).+");
    }

    public static Date dateActuelle() {
        LocalDate dateActuelle = LocalDate.now();
        // Convertir LocalDate en java.sql.Date
        return Date.valueOf(dateActuelle);
    }

    // Methodes se rattachant à UtilisateurDAO et ValideurDAO
    //====================================================================================//

    public static boolean enregistrerNouvelUtilisateur(String nom, String prenom, String email, String adresse, String password, UtilisateurDAO utilisateurDAO) throws SQLException {

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
        nouvelUtilisateur = new Utilisateur(nom, prenom, adresse, email, password);
        utilisateurDAO.ajouterUtilisateur(nouvelUtilisateur);
        System.out.println("Nouvel utilisateur enregistré avec succès.");
        return true;
    }

    public static boolean enregistrerNouveauValideur(String nom, String prenom, String email, String adresse, String password, ValideurDAO valideurDAO) throws SQLException, SQLException {

        if (!emailValide(email)) {
            System.out.println("Email invalide.");
            return false;
        }

        if (!passwordValide(password)) {
            System.out.println("Mot de passe invalide. Il doit contenir au moins une lettre et un chiffre.");
            return false;
        }

        if (valideurDAO.emailExiste(email)) {
            System.out.println("L'email existe déjà.");
            return false;
        }

        Valideur nouveauValideur;
        nouveauValideur = new Valideur(nom, prenom, adresse, email, password);
        valideurDAO.ajouterValideur(nouveauValideur);
        System.out.println("Nouvel utilisateur enregistré avec succès.");
        return true;
    }

    public static boolean supprimerUtilisateur(String email, String motDePasse, UtilisateurDAO utilisateurDAO) throws SQLException {
        boolean utilisateurValide = utilisateurDAO.verifierIdentifiants(email, motDePasse);

        if (utilisateurValide) {
            int idUtilisateur = utilisateurDAO.trouverUtilisateurParEmail(email);
            boolean utilisateurSupprime = utilisateurDAO.supprimerUtilisateur(idUtilisateur);
            if (utilisateurSupprime) {
                System.out.println("Utilisateur supprimé avec succès.");
                return true;
            } else {
                System.out.println("Échec de la suppression de l'utilisateur.");
                return false;
            }
        } else {
            System.out.println("Identifiants incorrects. Impossible de supprimer l'utilisateur.");
            return false;
        }
    }

    public static boolean connexionUtilisateur(String email, String password) throws SQLException {
        return UtilisateurDAO.verifierIdentifiants(email, password);
    }

    public static boolean connexionValideur(String email, String password) throws SQLException {
        return ValideurDAO.verifierIdentifiants(email, password);
    }

    // creer l'utilisateur courant
    public static Utilisateur recupererUtilisateurConnecte(String email, UtilisateurDAO utilisateurDAO) throws SQLException {
        Utilisateur user = null;
        int id = utilisateurDAO.trouverUtilisateurParEmail(email);
        user = utilisateurDAO.stockerUtilisateurParId(id);
        if (user == null) {
            System.out.println("Connexion impossible");
        }
        else {
            System.out.println("Utilisateur connecté !");
        }
        return user;
    }

    // creer le valideur courant
    public static Valideur recupererValideurConnecte(String email, ValideurDAO valideurDAO) throws SQLException {
        Valideur valideur = null;
        int id = valideurDAO.trouverValideurParEmail(email);
        valideur = valideurDAO.stockerValideurParId(id);
        if (valideur == null) {
            System.out.println("Connexion impossible");
        }
        else {
            System.out.println("Valideur connecté !");
        }
        return valideur;
    }

    // Renvoie l'id d'un utilisateur
    public static int getUserId(Utilisateur utilisateur, UtilisateurDAO utilisateurDAO) throws SQLException {
        return utilisateurDAO.trouverUtilisateurParEmail(utilisateur.getEmail());
        }

    // Renvoie l'id d'un valideur
    public static int getValideurId(Valideur valideur, ValideurDAO valideurDAO) throws SQLException {
        return valideurDAO.trouverValideurParEmail(valideur.getEmail());
    }

    // Methodes se rattachant à MissionDAO
    //====================================================================================//

    // utile pour les valideurs, pour savoir quelles missions sont en attentes de validation
    public static List<Mission> avoirToutesMissionsEnAttente(MissionDAO missionDAO) throws SQLException {
        return missionDAO.getAllMissionsEnAttente();
    }

    // utile pour les benevoles, pour savoir quelles missions ils peuvent accomplir
    public static List<Mission> avoirToutesMissionsValidees(MissionDAO missionDAO) throws SQLException {
        return missionDAO.getAllMissionsDemandeesValidees();
    }

    // utile pour les demandeurs, pour savoir quelles missions spontanées validées sont proposées
    public static List<Mission> avoirToutesMissionsSpontaneesValidees(MissionDAO missionDAO) throws SQLException {
        return missionDAO.getAllMissionsSpontaneesValidees();
    }

    // utile pour le benevole : "mon historique de missions réalisées"
    public static List<Mission> historiqueMissionsBenevole(Utilisateur benevole, UtilisateurDAO utilisateurDAO, MissionDAO missionDAO) throws SQLException {
        int id_benevole = getUserId(benevole, utilisateurDAO);
        return missionDAO.getMissionsPasseesBenevole(id_benevole);
    }

    // utile pour le demandeur : "mes missions en cours"
    public static List<Mission> missionsEnCoursDemandeur(Utilisateur demandeur, UtilisateurDAO utilisateurDAO, MissionDAO missionDAO) throws SQLException {
        int id_demandeur = getUserId(demandeur, utilisateurDAO);
        return missionDAO.getMissionsEnCoursDemandeur(id_demandeur);
    }

    // utile pour le demandeur : "mon historique de missions demandées"
    public static List<Mission> historiqueMissionsDemandeur(Utilisateur demandeur, UtilisateurDAO utilisateurDAO, MissionDAO missionDAO) throws SQLException {
        int id_demandeur = getUserId(demandeur, utilisateurDAO);
        return missionDAO.getMissionsPasseesDemandeur(id_demandeur);
    }

    // utile pour le benevole : "mes missions acceptées, en cours de réalisation..."
    public static List<Mission> missionsEnCoursBenevole(Utilisateur benevole, UtilisateurDAO utilisateurDAO, MissionDAO missionDAO) throws SQLException {
        int id_benevole = getUserId(benevole, utilisateurDAO);
        return missionDAO.getMissionsEnCoursBenevole(id_benevole);
    }

    // Demandeur qui ajoute une mission
    public static void demanderMission (Utilisateur demandeur, String description, MissionDAO missionDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        int id_demandeur = getUserId(demandeur, utilisateurDAO);
        missionDAO.ajouterMission(id_demandeur, description, dateActuelle(), "en attente");
    }

    // Bénévole qui propose une mission spontanée
    public static void proposerMission (Utilisateur benevole, String description, MissionDAO missionDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        int id_benevole = getUserId(benevole, utilisateurDAO);
        missionDAO.ajouterMissionSpontannee(id_benevole, description, dateActuelle(), "en attente");
    }

    // Valideur qui s'assigne une mission et la valide
    public static void validerMission (Valideur valideur, int id_mission, MissionDAO missionDAO, ValideurDAO valideurDAO) throws SQLException {
        int id_valideur = getValideurId(valideur, valideurDAO);
        missionDAO.ajouterValideur(id_valideur, id_mission);
        missionDAO.majStatutMission(id_mission, "validée");
    }

    // Valideur qui s'assigne une mission, la refuse et ajoute un motif de refus
    public static void refuserMission (Valideur valideur, int id_mission, String motif_refus,MissionDAO missionDAO, ValideurDAO valideurDAO) throws SQLException {
        int id_valideur = getValideurId(valideur, valideurDAO);
        missionDAO.ajouterValideur(id_valideur, id_mission);
        missionDAO.majStatutMission(id_mission, "refusée");
        missionDAO.justifierRefus(id_mission, motif_refus);
    }

    // Benevole qui accepte de faire une mission
    public static void accepterMissionDemandee(Utilisateur benevole, int id_mission, MissionDAO missionDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        if (Objects.equals(missionDAO.getStatutMission(id_mission), "validée")) {
            int id_benevole = getUserId(benevole, utilisateurDAO);
            missionDAO.ajouterBenevole(id_benevole, id_mission);
            missionDAO.majStatutMission(id_mission, "acceptée");
        }
        else{
            System.out.println("Impossible d'accepter une mission non validée");
        }
    }

    // Demandeur qui accepte une mission spontanéee proposée
    public static void accepterMissionSpontanee(Utilisateur demandeur, int id_mission, MissionDAO missionDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        if (Objects.equals(missionDAO.getStatutMission(id_mission), "validée")) {
            int id_demandeur = getUserId(demandeur, utilisateurDAO);
            missionDAO.ajouterDemandeur(id_demandeur, id_mission);
            missionDAO.majStatutMission(id_mission, "acceptée");
        }
        else{
            System.out.println("Impossible d'accepter une mission non validée");
        }

    }

    // Demandeur qui confirme la fin de la mission
    public static void finirMission(int id_mission, MissionDAO missionDAO) throws SQLException {
        missionDAO.majStatutMission(id_mission, "réalisée");
    }

    // Modifier la description d'une mission
    public static void modifierDescriptionMission(int id_mission, String nouvelleDescription, MissionDAO missionDAO) throws SQLException {
        missionDAO.majDescriptionMission(id_mission, nouvelleDescription);
    }

    // Methodes se rattachant à AvisDAO
    //====================================================================================//

    // l'utilisateur courant (benevole ou demandeur) donne son avis sur la mission
    public static void donnerAvis(int id_mission, Utilisateur auteur, int note, String commentaire, UtilisateurDAO utilisateurDAO, AvisDAO avisDAO, MissionDAO missionDAO) throws SQLException {
        if (Objects.equals(missionDAO.getStatutMission(id_mission), "réalisée")) {
            int id_auteur = getUserId(auteur, utilisateurDAO);
            avisDAO.ajouterAvis(id_mission, id_auteur, note, commentaire, dateActuelle());
            System.out.println("Merci pour votre avis !");
        }
        else {
            System.out.println("L'avis ne peut être donné tant que la mission n'est pas finie.");
        }
    }

    // récupérer un avis à partir de son auteur et de sa mission
    public static Avis recupererAvis(int id_mission, String email, AvisDAO avisDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        int id_auteur = utilisateurDAO.trouverUtilisateurParEmail(email);
        int id_avis = avisDAO.trouverAvisParAuteurEtMission(id_mission, id_auteur);
        return avisDAO.stockerAvis(id_avis);
    }

    // récupérer les avis d'une mission
    public static List<Avis> recupererLesAvisMission (int id_mission, AvisDAO avisDAO) throws SQLException {
        return avisDAO.getLesAvisMission(id_mission);
    }

    // récupérer les avis d'un utilisateur
    public static List<Avis> recupererLesAvisAuteur (String email, AvisDAO avisDAO, UtilisateurDAO utilisateurDAO) throws SQLException {
        int id_auteur = utilisateurDAO.trouverUtilisateurParEmail(email);
        return avisDAO.getLesAvisAuteur(id_auteur);
    }

    // interface graphique

    // redimensionner image
    public static ImageIcon redimImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // bouton avec style
    /*public static JButton creerBouton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(150, 30)); // Taille réduite et fixe
        return button;
    }*/

    public static JButton creerBouton(String text) {
        JButton button = new JButton(text);

        // Couleurs de fond et de texte
        button.setBackground(new Color(33, 33, 33)); // Fond noir-gris
        button.setForeground(Color.WHITE); // Texte blanc

        // Police et style du texte
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Taille fixe
        button.setPreferredSize(new Dimension(200, 50)); // Dimensions ajustées pour ressembler au bouton de l'image
        button.setFocusPainted(false);

        // Bordures arrondies
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        // Effet d'ombrage doux
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(33, 33, 33), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        return button;
    }





    /*

    ATTENTION : Tout ce qui est SQL dans les classes DAO
    les méthodes des classes DAO ne sont appelées que dans Utils.java
    les méthodes dans Utils.java sont les seules à pouvoir être appelées dans le main
    ou ailleurs (test notamment)
    Ne rien écrire / créer dans les classes objets "non DAO", puisque ce n'est que du local
    et rien dans la bdd

    À AJOUTER

    Ajouter une protection pour qu'un avis ne puisse être émis que par le demandeur de la mission
    un truc similaire à if (Objects.equals(missionDAO.getStatutMission(id_mission), "réalisée"))
    dans utils.donnerAvis pour verifier que l'auteur est bien le demandeur de la mission
    (peut-être ajouter une méthode dans AvisDAO qui prend en param l'id de la mission et retourne
     seulement l'id du demandeur de la mission, pour pouvoir faire le Objects.equals pour la sécurité)

    Moyenne des avis pour une personne impliquée dans une mission en tant que bénévole,
    peut-être ajouter une methode dans AvisDAO pour récupérer ces avis puis faire une fonction
    dans utils pour calculer la moyenne des avis.getNotes()

    Méthode pour pouvoir récupérer le nom, le prénom et l'adresse email de l'auteur d'un avis
    (faire un join dans AvisDAO ? Ensuite écrire la fonction haut niveau dans utils)

    Ajouter des "catégories", des "badges" pour les bénévoles en fonction de leur moyenne et du
    nombre de missions auxquelles ils ont participé (ex : si bénévole a une très bonne moyenne,
    et participe à beaucoup de missions, attribuer badge "super altruiste" etc....) pour cela
    créer sql dans MissionDAO et/ou AvisDAO (avec COUNT....) et fonction dans Utils.java qui retourne
    un string (le badge attribué)

    Autres idées
    ...

    tests unitaires et interface graphique






     */


}