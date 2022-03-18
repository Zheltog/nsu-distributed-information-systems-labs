package ru.nsu.ccfit.beloglazov.dis.dis2;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {

    public DatabaseInitializer() throws SQLException, IOException {
        DatabaseConnection.create();
    }

    public List<String> getTables() throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSet tables = DatabaseConnection.getConnection()
                .getMetaData()
                .getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            result.add(tables.getString("TABLE_NAME"));
        }
        return result;
    }

    public void createNodesTable() throws SQLException {
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

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.execute();
    }

    public void createTagsTable() throws SQLException {
        String sql = "create table tags (" +
                "node_id serial not null references nodes (id), " +
                "k varchar(255), " +
                "v varchar(255))";

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.execute();
    }
}