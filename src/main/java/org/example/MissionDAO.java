package org.example;
import java.sql.*;
import java.util.ArrayList;
import static org.example.DBConnection.getConnection;

//DAO signifie Data Access Object
public class MissionDAO {
    private Connection connection;

    //CONSTRUCTEUR
    public MissionDAO() throws SQLException {
        this.connection = getConnection(); // Utilise la classe DBConnection pour obtenir une connexion
    }

}
