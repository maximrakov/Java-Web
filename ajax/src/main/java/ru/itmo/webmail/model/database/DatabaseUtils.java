package ru.itmo.webmail.model.database;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.mariadb.jdbc.MariaDbDataSource;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static PropertiesConfiguration config = new PropertiesConfiguration();
    public static Connection getDataSource() {
        return DataSourceHolder.connection;
    }
    private static final class DataSourceHolder {
        private static Connection connection;

        static {

            try {
                File file = new File("");
                config.load("..\\webapps\\ROOT\\WEB-INF\\classes\\application.properties");
            } catch (ConfigurationException e) {
                throw new RuntimeException(e);
            }
            final String URL = config.getString("database.url");
            final String USERNAME = config.getString("database.user");
            final String PASSWORD = config.getString("database.password");
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
