package ru.nsu.ccfit.beloglazov.dis.dis3.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Dao {

    protected final Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getInsertStatementSql();

    public abstract PreparedStatement getPreparedStatement() throws SQLException;

    protected abstract String getEmptyInsertStatementSql();
}