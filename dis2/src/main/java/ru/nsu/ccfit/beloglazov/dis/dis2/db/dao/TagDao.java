package ru.nsu.ccfit.beloglazov.dis.dis2.db.dao;

import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;

public class TagDao extends Dao<Tag> {

    private final Node node;

    public TagDao(Node node, Connection connection) {
        super(connection);
        this.node = node;
    }

    @Override
    public String sqlInsertFor(Tag tag) {
        return "insert into tags (" +
                "node_id, k, v" +
                ") values (" +
                node.getId() + ", " +
                tag.getK() + ", " +
                tag.getV() + ")";
    }
}