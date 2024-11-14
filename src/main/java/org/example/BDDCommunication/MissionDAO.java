package org.example.BDDCommunication;
import org.example.ClassesLocales.Mission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO signifie Data Access Object
public class MissionDAO {
    private Connection connection;

    //CONSTRUCTEUR
    public MissionDAO(Connection connection) throws SQLException {
        this.connection = connection; // Utilise la classe DBConnection pour obtenir une connexion
    }

    //METHODES

    public  Connection getConnectionMissionDAO() {
        return this.connection;
    }

    //ajouter mission avec demandeur
    public void ajouterMission(int id_demandeur, String description, java.sql.Date date_mission, String statut) throws SQLException {
        String sql = "INSERT INTO missions (id_demandeur, description, date_mission, statut) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_demandeur);
        statement.setString(2, description);
        statement.setDate(3, date_mission);
        statement.setString(4, statut);
        statement.executeUpdate();
    }

    //ajouter mission avec benevole
    public void ajouterMissionSpontannee(int id_benevole, String description, java.sql.Date date_mission, String statut) throws SQLException {
        String sql = "INSERT INTO missions (id_benevole, description, date_mission, statut) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_benevole);
        statement.setString(2, description);
        statement.setDate(3, date_mission);
        statement.setString(4, statut);
        statement.executeUpdate();
    }

    // un valideur s'ajoute sur une mission existante
    public void ajouterValideur(int id_valideur, int id_mission) throws SQLException {
        // Vérification si la mission a déjà un valideur
        String checkSql = "SELECT id_valideur FROM missions WHERE id_mission = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setInt(1, id_mission);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            int currentValideur = resultSet.getInt("id_valideur");

            // Si id_valideur est null (0 dans le cas d'un INT), alors la mission n'a pas de valideur
            if (currentValideur == 0) {
                String updateSql = "UPDATE missions SET id_valideur = ? WHERE id_mission = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setInt(1, id_valideur);
                updateStatement.setInt(2, id_mission);
                updateStatement.executeUpdate();
                System.out.println("Le valideur a été ajouté à la mission avec succès.");
            } else {
                System.out.println("La mission a déjà un valideur.");
            }
        } else {
            System.out.println("Mission non trouvée.");
        }
    }

    public void justifierRefus(int id_mission, String motif_refus) throws SQLException {
        String sql = "UPDATE missions SET motif_refus = ? WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, motif_refus);
        statement.setInt(2, id_mission);
        int majOK = statement.executeUpdate();

        if (majOK > 0) {
            System.out.println("Le motif de refus a été saisi.");
        } else {
            System.out.println("Mission non trouvée.");
        }
    }

    // récupérer le statut d'une mission à partir de son id
    public String getStatutMission(int id_mission) throws SQLException {
        String sql = "SELECT statut FROM missions WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_mission);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("statut");
        } else {
            System.out.println("Mission non trouvée pour l'ID : " + id_mission);
            return null;
        }
    }

    // pour les missions demandées par un demandeur
    public void ajouterBenevole(int id_benevole, int id_mission) throws SQLException {
        // Vérifie d'abord si la mission existe et si elle est validée
        String checkSql = "SELECT id_mission FROM missions WHERE id_mission = ? AND (statut = 'validée')";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setInt(1, id_mission);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            String updateSql = "UPDATE missions SET id_benevole = ? WHERE id_mission = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, id_benevole);
            updateStatement.setInt(2, id_mission);
            int majOK = updateStatement.executeUpdate();

            if (majOK > 0) {
                System.out.println("Le bénévole a été ajouté à la mission avec succès.");
            } else {
                System.out.println("Aucune mise à jour effectuée.");
            }
        } else {
            System.out.println("Mission non trouvée ou non validée.");
        }
    }

    // pour les missions spontanées
    public void ajouterDemandeur(int id_demandeur, int id_mission) throws SQLException {
        // Vérifie d'abord si la mission existe et si elle est validée
        String checkSql = "SELECT id_mission FROM missions WHERE id_mission = ? AND (statut = 'validée')";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setInt(1, id_mission);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            String updateSql = "UPDATE missions SET id_demandeur = ? WHERE id_mission = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, id_demandeur);
            updateStatement.setInt(2, id_mission);
            int majOK = updateStatement.executeUpdate();

            if (majOK > 0) {
                System.out.println("Le demandeur a été ajouté à la mission avec succès.");
            } else {
                System.out.println("Aucune mise à jour effectuée.");
            }
        } else {
            System.out.println("Mission non trouvée.");
        }
    }

    public void majStatutMission(int id_mission, String nouveauStatut) throws SQLException {
        String sql = "UPDATE missions SET statut = ? WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nouveauStatut);
        statement.setInt(2, id_mission);
        int majOK = statement.executeUpdate();

        if (majOK > 0) {
            System.out.println("Statut de la mission mis à jour avec succès.");
        } else {
            System.out.println("Mission non trouvée.");
        }
    }

    // Modifier la description d'une mission
    public void majDescriptionMission(int id_mission, String nouvelleDescription) throws SQLException {
        String sql = "UPDATE missions SET description = ? WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nouvelleDescription);
        statement.setInt(2, id_mission);
        int majOK = statement.executeUpdate();

        if (majOK > 0) {
            System.out.println("Description de la mission mise à jour avec succès.");
        } else {
            System.out.println("Mission non trouvée.");
        }
    }

    public Mission stockerMissionParId(int id_mission) throws SQLException {
        // déclare une variable pour stocker la mission
        Mission mission = null;

        String sql = "SELECT statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission FROM missions WHERE id_mission = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_mission);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int id_demandeur = resultSet.getInt("id_demandeur");
            int id_benevole = resultSet.getInt("id_benevole");
            int id_valideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            mission = new Mission(id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date);
        } else {
            System.out.println("Mission non trouvée avec l'ID : " + id_mission);
        }

        return mission;
    }

    // utile pour le demandeur : "mes missions en cours"
    public List<Mission> getMissionsEnCoursDemandeur(int id_demandeur) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE id_demandeur = ? AND (statut = 'en attente' OR statut = 'validée' OR statut = 'acceptée')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_demandeur);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idBenevole = resultSet.getInt("id_benevole");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, id_demandeur, idBenevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour le demandeur : "mon historique de missions demandées"
    public List<Mission> getMissionsPasseesDemandeur(int id_demandeur) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE id_demandeur = ? AND (statut = 'refusée' OR statut = 'réalisée')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_demandeur);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idBenevole = resultSet.getInt("id_benevole");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, id_demandeur, idBenevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour le benevole : "mes missions acceptées, en cours de réalisation..."
    public List<Mission> getMissionsEnCoursBenevole(int id_benevole) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE id_benevole = ? AND (statut = 'acceptée')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_benevole);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idDemandeur = resultSet.getInt("id_demandeur");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, idDemandeur, id_benevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour le benevole : "mon historique de missions réalisées"
    public List<Mission> getMissionsPasseesBenevole(int id_benevole) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE id_benevole = ? AND (statut = 'réalisée')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_benevole);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idDemandeur = resultSet.getInt("id_demandeur");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, idDemandeur, id_benevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour les valideurs, pour savoir quelles missions sont en attentes de validation
    public List<Mission> getAllMissionsEnAttente() throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE statut = 'en attente'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idDemandeur = resultSet.getInt("id_demandeur");
            int id_benevole = resultSet.getInt("id_benevole");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, idDemandeur, id_benevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour les benevoles, pour savoir quelles missions sont en attentes d'acceptation
    public List<Mission> getAllMissionsDemandeesValidees() throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE statut = 'validée' AND id_demandeur IS NOT NULL";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idDemandeur = resultSet.getInt("id_demandeur");
            int id_benevole = resultSet.getInt("id_benevole");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, idDemandeur, id_benevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }

    // utile pour les demandeurs, pour savoir quelles missions spontanées validées sont proposées
    public List<Mission> getAllMissionsSpontaneesValidees() throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT id_mission, statut, description, id_demandeur, id_benevole, id_valideur, motif_refus, date_mission " +
                "FROM missions WHERE statut = 'validée' AND id_benevole IS NOT NULL";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id_mission = resultSet.getInt("id_mission");
            String statut = resultSet.getString("statut");
            String description = resultSet.getString("description");
            int idDemandeur = resultSet.getInt("id_demandeur");
            int id_benevole = resultSet.getInt("id_benevole");
            int idValideur = resultSet.getInt("id_valideur");
            String motif_refus = resultSet.getString("motif_refus");
            java.sql.Date date = resultSet.getDate("date_mission");

            Mission mission = new Mission(id_mission, statut, description, idDemandeur, id_benevole, idValideur, motif_refus, date);
            missions.add(mission);
        }

        return missions;
    }



}

