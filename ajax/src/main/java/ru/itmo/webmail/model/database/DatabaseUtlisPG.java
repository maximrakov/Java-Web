package ru.itmo.webmail.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtlisPG {
    private static final String URL = "jdbc:postgresql://localhost:5432/webLab5";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pqqk9a9s";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
