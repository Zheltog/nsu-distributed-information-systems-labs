package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

    private final Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    protected void insert(ExecuteStrategy es, String sql) throws SQLException {
        switch (es) {
            case STATEMENT:
                executeViaStatement(sql);
                break;
            case PREPARED_STATEMENT:
                executeViaPreparedStatement(sql);
                break;
        }
    }

    private void executeViaStatement(String sql) throws SQLException {
        this.connection.createStatement().execute(sql);
    }

    private void executeViaPreparedStatement(String sql) throws SQLException {
        this.connection.prepareStatement(sql).execute();
    }
}