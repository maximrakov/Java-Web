package ru.itmo.webmail.model.database;

import org.mariadb.jdbc.MariaDbDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    public static Connection getDataSource() {
        return DataSourceHolder.connection;
    }

    private static final class DataSourceHolder {
        private static Connection connection;

        static {
            final String URL = "jdbc:postgresql://localhost:5432/webLab5";
            final String USERNAME = "postgres";
            final String PASSWORD = "pqqk9a9s";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
