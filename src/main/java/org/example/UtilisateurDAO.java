package org.example;
import java.sql.*;
import java.util.ArrayList;
import static org.example.DBConnection.getConnection;

//DAO signifie Data Access Object
public class UtilisateurDAO {
    private Connection connection;

    //CONSTRUCTEUR
    public UtilisateurDAO() throws SQLException {
        this.connection = getConnection(); // Utilise la classe DBConnection pour obtenir une connexion
    }

    // ajouter un utilisateur dans la bdd
    public void ajouterUtilisateur(Utilisateur utilisateur, String statut) throws SQLException {
        String sql = "INSERT INTO utilisateurs (nom, email, adresse, type_utilisateur, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getEmail());
        statement.setString(3, utilisateur.getAdresse());
        statement.setString(4, statut); // bénévole, demandeur, valideur
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

    // à utiliser lors de la création d'un nouveau compte pour verifier que le mail n'existe pas avant
    // true si le mail existe, false sinon
    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM utilisateurs WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0; //retourne true si le compte est supérieur à 0
        }
        return false;
    }

    public int verifierIdentifiants(String email, String password) throws SQLException {
        String sql = "SELECT type_utilisateur FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        // si l'utilisateur existe
        if (resultSet.next()) {
            String typeUtilisateur = resultSet.getString("type_utilisateur");
            switch (typeUtilisateur) {
                case "demandeur":
                    return 1;
                case "bénévole":
                    return 2;
                case "valideur":
                    return 3;
                default:
                    return -1;
            }
        }
        return -1; //mot de passe ou mail incorrect
    }




}
