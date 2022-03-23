package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;
import java.sql.SQLException;

public class TagDao extends Dao {

    public TagDao(Connection connection) {
        super(connection);
    }

    public void insert(ExecuteStrategy es, Node node, Tag tag) throws SQLException {
        insert(es, sqlInsertFor(node, tag));
    }

    private String sqlInsertFor(Node node, Tag tag) {
        return "insert into tags (" +
                "node_id, k, v" +
                ") values (" +
                node.getId() + ", '" +
                tag.getK() + "', '" +
                tag.getV() + "')";
    }
}