package org.example.BDDCommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_001";
    private static final String USER = "projet_gei_001";
    private static final String PASSWORD = "AhK2Mahp";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }}
