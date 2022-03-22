package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {

    private static final Logger log = Logger.getLogger(DatabaseInitializer.class);

    public static void initializeMissingTables(Connection connection) {
        try {
            log.info("Successfully connected to local database");
            List<String> tables = DatabaseInitializer.getTables(connection);
            log.info("Tables in database: " + tables);
            if (!tables.contains("nodes")) {
                DatabaseInitializer.createNodesTable(connection);
                log.info("Created table 'nodes'");
            }
            if (!tables.contains("tags")) {
                DatabaseInitializer.createTagsTable(connection);
                log.info("Created table 'tags'");
            }
            log.info("Missing tables initialization finished successfully");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    private static List<String> getTables(Connection connection) throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSet tables = connection
                .getMetaData()
                .getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            result.add(tables.getString("TABLE_NAME"));
        }
        return result;
    }

    private static void createNodesTable(Connection connection) throws SQLException {
        String sql = "create table nodes (" +
                "id serial not null primary key, " +
                "lat numeric(10), " +
                "lon numeric(10), " +
                "usr varchar(255), " +
                "uid bigint, " +
                "visible boolean, " +
                "version bigint, " +
                "changeset bigint, " +
                "timestamp timestamp)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
    }

    private static void createTagsTable(Connection connection) throws SQLException {
        String sql = "create table tags (" +
                "node_id serial not null references nodes (id), " +
                "k varchar(255), " +
                "v varchar(255))";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
    }
}