package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NodeDao extends Dao {

    public NodeDao(Connection connection) {
        super(connection, "nodes");
    }

    public void insert(ExecuteStrategy es, Node node) throws SQLException {
        insert(es, sqlInsert(node));
    }

    private String sqlInsert(Node node) {
        return "insert into nodes (" +
                "id, lat, lon, usr, uid, visible, version, changeset, timestamp" +
                ") values (" +
                node.getId() + ", " +
                node.getLat() + ", " +
                node.getLon() + ", '" +
                node.getUser() + "', " +
                node.getUid() + ", " +
                node.isVisible() + ", " +
                node.getVersion() + ", " +
                node.getChangeset() + ", " +
                "timestamp '" +
                Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()) + "')";
    }
}