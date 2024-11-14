package org.example.BDDCommunication;
import org.example.ClassesLocales.Valideur;

import java.sql.*;

import static org.example.BDDCommunication.DBConnection.getConnection;

//DAO signifie Data Access Object
public class ValideurDAO {
    private static Connection connection;

    //CONSTRUCTEUR
    public ValideurDAO() throws SQLException {
        this.connection = getConnection(); // Utilise la classe DBConnection pour obtenir une connexion
    }

    //METHODES

    public static Connection getConnectionValideurDAO() {
        return connection;
    }

    // ajouter un utilisateur dans la bdd
    public void ajouterValideur(Valideur valideur) throws SQLException {
        String sql = "INSERT INTO valideurs (prenom, nom, email, adresse, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, valideur.getPrenom());
        statement.setString(2, valideur.getNom());
        statement.setString(3, valideur.getEmail());
        statement.setString(4, valideur.getAdresse());
        statement.setString(5, valideur.getPassword());
        statement.executeUpdate(); //Exécute la requete
    }

    public boolean supprimerValideur(int idValideur) throws SQLException {
        String sql = "DELETE FROM valideurs WHERE id_valideur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idValideur);

        int valideurTrouve = statement.executeUpdate(); //retourne le nb de valideur trouvés
        // 1 normalement car id unique, 0 s'il n'existe pas

        if (valideurTrouve > 0) {
            System.out.println("Valideur supprimé avec succès.");
        } else {
            System.out.println("Aucun valideur trouvé avec cet ID.");
        }
        return valideurTrouve > 0;
    }

    // L'utilisateur ne connait pas son ID
    public int trouverValideurParEmail(String email) throws SQLException {
        String sql = "SELECT id_valideur FROM valideurs WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) { //si utilisateur trouvé
            return resultSet.getInt("id_valideur");
        } else {
            System.out.println("Aucun valideur trouvé avec cet email.");
            return -1; // si aucun valideur n'est trouvé :(
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
        String sql = "SELECT id_valideur FROM valideurs WHERE email = ? AND mot_de_passe = ?";

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

    public Valideur stockerValideurParId(int id_valideur) throws SQLException {
        // Déclare une variable pour stocker l'utilisateur
        Valideur valideur = null;

        String sql = "SELECT prenom, nom, email, adresse, mot_de_passe FROM valideurs WHERE id_valideur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_valideur);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String prenom = resultSet.getString("prenom");
            String nom = resultSet.getString("nom");
            String email = resultSet.getString("email");
            String adresse = resultSet.getString("adresse");
            String password = resultSet.getString("mot_de_passe");

            valideur = new Valideur(nom, prenom, adresse, email, password);
        } else {
            System.out.println("Utilisateur non trouvé avec l'ID : " + id_valideur);
        }

        return valideur;
    }

}
