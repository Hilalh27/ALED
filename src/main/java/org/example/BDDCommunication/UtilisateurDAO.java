package org.example.BDDCommunication;
import org.example.ClassesLocales.Utilisateur;

import java.sql.*;

import static org.example.BDDCommunication.DBConnection.getConnection;

//DAO signifie Data Access Object
public class UtilisateurDAO {
    private static Connection connection;

    //CONSTRUCTEURS
    public UtilisateurDAO() throws SQLException {
        this.connection = getConnection(); // Utilise la classe DBConnection pour obtenir une connexion
    }

    public UtilisateurDAO(Connection connection) throws SQLException {
        this.connection = connection; // Utilise la classe DBConnection pour obtenir une connexion
    }

    //METHODES

    public static Connection getConnectionUtilisateurDAO() {
        return connection;
    }

    // ajouter un utilisateur dans la bdd
    public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateurs (prenom, nom, email, adresse, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, utilisateur.getPrenom());
        statement.setString(2, utilisateur.getNom());
        statement.setString(3, utilisateur.getEmail());
        statement.setString(4, utilisateur.getAdresse());
        statement.setString(5, utilisateur.getPassword());
        statement.executeUpdate(); //Exécute la requete
    }

    public boolean supprimerUtilisateur(int idUtilisateur) throws SQLException {
        String sql = "DELETE FROM utilisateurs WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUtilisateur);

        int utilisateurTrouve = statement.executeUpdate(); //retourne le nb d'utilisateurs trouvés
                                                            // 1 normalement car id unique, 0 s'il n'existe pas

        if (utilisateurTrouve > 0) {
            System.out.println("Utilisateur supprimé avec succès.");
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet ID.");
        }
        return utilisateurTrouve > 0;
    }

    // L'utilisateur ne connait pas son ID
    public int trouverUtilisateurParEmail(String email) throws SQLException {
        String sql = "SELECT id_utilisateur FROM utilisateurs WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) { //si utilisateur trouvé
            return resultSet.getInt("id_utilisateur");
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet email.");
            return -1; // si aucun utilisateur n'est trouvé :(
        }
    }

    public static String trouverPrenomUtilisateurParId(int id) throws SQLException {
        String sql = "SELECT prenom FROM utilisateurs WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(id));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) { //si utilisateur trouvé
            return resultSet.getString("prenom");
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet id.");
            return ""; // si aucun utilisateur n'est trouvé :(
        }
    }

    public static String trouverAdresseUtilisateurParId(int id) throws SQLException {
        String sql = "SELECT adresse FROM utilisateurs WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(id));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) { //si utilisateur trouvé
            return resultSet.getString("adresse");
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet id.");
            return ""; // si aucun utilisateur n'est trouvé :(
        }
    }

    // à utiliser lors de la création d'un nouveau compte pour verifier que le mail n'existe pas avant
    // true si le mail existe, false sinon
    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT (EXISTS (SELECT 1 FROM utilisateurs WHERE email = ?) " +
                "OR EXISTS (SELECT 1 FROM valideurs WHERE email = ?)) AS email_exists";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean("email_exists"); // Retourne true si l'email existe dans l'une des tables
        }
        return false;
    }


    //verifie si mail et mdp correspondent pour accéder au compte
    public static boolean verifierIdentifiants(String email, String password) throws SQLException {
        String sql = "SELECT id_utilisateur FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        // si l'utilisateur existe
        if (resultSet.next()) {
            System.out.println("Mot de passe et mail correspondent.");
            return true;
        }
        else {
            System.out.println("Mot de passe ou mail incorrect.");
            return false; //mot de passe ou mail incorrect
        }
    }

    public Utilisateur stockerUtilisateurParId(int id_utilisateur) throws SQLException {
        // Déclare une variable pour stocker l'utilisateur
        Utilisateur utilisateur = null;

        String sql = "SELECT prenom, nom, email, adresse, mot_de_passe FROM utilisateurs WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_utilisateur);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String prenom = resultSet.getString("prenom");
            String nom = resultSet.getString("nom");
            String email = resultSet.getString("email");
            String adresse = resultSet.getString("adresse");
            String password = resultSet.getString("mot_de_passe");

            utilisateur = new Utilisateur(nom, prenom, adresse, email, password);
        } else {
            System.out.println("Utilisateur non trouvé avec l'ID : " + id_utilisateur);
        }

        return utilisateur;
    }

}
