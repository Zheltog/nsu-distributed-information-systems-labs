package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import java.sql.Connection;

public class NodeDao extends Dao<Node> {

    public NodeDao(Connection connection) {
        super(connection);
    }

    @Override
    public String sqlInsertFor(Node node) {
        return "insert into nodes (" +
                "id, lat, lon, usr, uid, visible, version, changeset, timestamp" +
                ") values (" +
                node.getId() + ", " +
                node.getLat() + ", " +
                node.getLon() + ", " +
                node.getUser() + ", " +
                node.getUid() + ", " +
                node.isVisible() + ", " +
                node.getVersion() + ", " +
                node.getChangeset() + ", " +
                node.getTimestamp() + ")";
    }
}