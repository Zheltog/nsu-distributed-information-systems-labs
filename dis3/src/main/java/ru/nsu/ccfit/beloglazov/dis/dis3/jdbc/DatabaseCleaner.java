package ru.nsu.ccfit.beloglazov.dis.dis3.jdbc;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseCleaner {

    private static final Logger log = Logger.getLogger(DatabaseCleaner.class);

    public static void cleanTables(Connection connection) {
        try {
            connection.prepareStatement(
                    "truncate table tags cascade;" +
                            "truncate table nodes cascade;").execute();
            connection.commit();
//            log.info("Tables data cleaning finished successfully");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}