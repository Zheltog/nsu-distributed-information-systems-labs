package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T> {

    private final Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public void saveViaStatement(T obj) throws SQLException {
        Statement s = connection.createStatement();
        s.executeQuery(sqlInsertFor(obj));
    }

    public void saveViaPreparedStatement(T obj) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlInsertFor(obj));
        ps.executeQuery();
    }

    public abstract String sqlInsertFor(T obj);
}