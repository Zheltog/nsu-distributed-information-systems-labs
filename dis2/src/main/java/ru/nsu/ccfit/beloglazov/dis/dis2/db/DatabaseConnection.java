package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, IOException {
        return (connection == null) ? createConnection() : connection;
    }

    private static Connection createConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
        String url = properties.getProperty("URL");
        String login = properties.getProperty("LOGIN");
        String password = properties.getProperty("PASSWORD");
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }
}