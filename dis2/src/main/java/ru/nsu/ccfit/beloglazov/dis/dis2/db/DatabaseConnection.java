package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final Logger log = Logger.getLogger(DatabaseConnection.class);

    private static Connection connection;

    public static Connection getConnection() {
        return (connection == null) ? createConnection() : connection;
    }

    private static Connection createConnection() {
        try {
            Properties properties = new Properties();
            properties.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
            String url = properties.getProperty("URL");
            String login = properties.getProperty("LOGIN");
            String password = properties.getProperty("PASSWORD");
            connection = DriverManager.getConnection(url, login, password);
//            log.info("New database connection created successfully");
            return connection;
        } catch (IOException | SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}