package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO signifie Data Access Object
public class AvisDAO {
    private Connection connection;

    //CONSTRUCTEUR
    public AvisDAO(Connection connection) throws SQLException {
        this.connection = connection; // Utilise la classe DBConnection pour obtenir une connexion
    }

    //METHODES

    public Connection getConnectionAvisDAO() {
        return this.connection;
    }

    public void ajouterAvis(int id_mission, int id_utilisateur_auteur, int note, String commentaire, java.sql.Date date_avis) throws SQLException {
        String sql = "INSERT INTO avis (id_mission, id_utilisateur_auteur, note, commentaire, date_avis) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_mission);
        statement.setInt(2, id_utilisateur_auteur);
        statement.setInt(3, note);
        statement.setString(4, commentaire);
        statement.setDate(5, date_avis);
        statement.executeUpdate();
    }

    public int trouverAvisParAuteurEtMission(int id_mission, int id_utilisateur_auteur) throws SQLException {
        String requete = "SELECT id_avis FROM avis WHERE id_utilisateur_auteur = ? AND id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(requete);

        statement.setInt(1, id_utilisateur_auteur);
        statement.setInt(2, id_mission);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id_avis"); // Récupère l'identifiant de l'avis
        } else {
            System.out.println("Aucun avis trouvé pour cet utilisateur et cette mission.");
            return -1; // si aucun avis n'est trouvé :(
        }
    }

    // récupérer un avis à partir de son id
    public Avis stockerAvis(int idAvis) throws SQLException {
        // déclare une variable pour stocker l'avis
        Avis avis = null;
        String requete = "SELECT * FROM avis WHERE id_avis = ?";
        PreparedStatement statement = connection.prepareStatement(requete);
        statement.setInt(1, idAvis);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            int id_utilisateur_auteur = resultSet.getInt("id_utilisateur_auteur");
            int note = resultSet.getInt("note");
            String commentaire = resultSet.getString("commentaire");
            java.sql.Date date_avis = resultSet.getDate("date_avis");
            avis = new Avis(id_mission, id_utilisateur_auteur, note, commentaire, date_avis);
        } else {
            System.out.println("Avis non trouvé pour l'ID : " + idAvis);
        }
        return avis;
    }

    // récupérer les avis d'une mission
    public List<Avis> getLesAvisMission(int id_mission) throws SQLException {
        List<Avis> les_avis = new ArrayList<>();
        String sql = "SELECT * FROM avis WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_mission);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_utilisateur_auteur = resultSet.getInt("id_utilisateur_auteur");
            int note = resultSet.getInt("note");
            String commentaire = resultSet.getString("commentaire");
            java.sql.Date date_avis = resultSet.getDate("date_avis");
            Avis avis = new Avis(id_mission, id_utilisateur_auteur, note, commentaire, date_avis);

            les_avis.add(avis);
        }

        return les_avis;
    }

    // récupérer les avis d'un auteur d'avis (= avis rédigés par un utilisateur)
    public List<Avis> getLesAvisAuteur(int id_utilisateur_auteur) throws SQLException {
        List<Avis> les_avis = new ArrayList<>();
        String sql = "SELECT * FROM avis WHERE id_utilisateur_auteur = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_utilisateur_auteur);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            int note = resultSet.getInt("note");
            String commentaire = resultSet.getString("commentaire");
            java.sql.Date date_avis = resultSet.getDate("date_avis");
            Avis avis = new Avis(id_mission, id_utilisateur_auteur, note, commentaire, date_avis);

            les_avis.add(avis);
        }

        return les_avis;
    }

}