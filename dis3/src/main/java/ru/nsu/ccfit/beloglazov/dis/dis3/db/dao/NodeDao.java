package ru.nsu.ccfit.beloglazov.dis.dis3.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis3.generated.Node;
import java.sql.*;

public class NodeDao extends Dao {

    private final Node node;

    public NodeDao(Connection connection, Node node) {
        super(connection);
        this.node = node;
    }

    @Override
    public String getInsertStatementSql() {
        return "insert into nodes (" +
                "id, lat, lon, usr, uid, visible, version, changeset, timestamp" +
                ") values (" +
                node.getId() + ", " +
                node.getLat() + ", " +
                node.getLon() + ", '" +
                node.getUser().replace('\'', '\\') + "', " +
                node.getUid() + ", " +
                node.isVisible() + ", " +
                node.getVersion() + ", " +
                node.getChangeset() + ", " +
                "timestamp '" +
                Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()) + "')";
    }

    @Override
    public PreparedStatement getPreparedStatement() throws SQLException {
        PreparedStatement ps =  connection.prepareStatement(getEmptyInsertStatementSql());
        ps.setInt(1, node.getId().intValue());
        ps.setDouble(2, node.getLat());
        ps.setDouble(3, node.getLon());
        ps.setString(4, node.getUser());
        ps.setInt(5, node.getUid().intValue());
        ps.setObject(6, node.isVisible(), Types.BOOLEAN);
//        if (node.isVisible() != null) {
//            ps.setBoolean(6, node.isVisible());
//        } else {
//            ps.setNull(6, NULL);
//        }
        ps.setInt(7, node.getVersion().intValue());
        ps.setInt(8, node.getChangeset().intValue());
        ps.setTimestamp(9, Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()));
        return ps;
    }

    @Override
    protected String getEmptyInsertStatementSql() {
        return "insert into nodes (" +
                "id, lat, lon, usr, uid, visible, version, changeset, timestamp" +
                ") values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}