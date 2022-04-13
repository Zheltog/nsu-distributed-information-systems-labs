package ru.nsu.ccfit.beloglazov.dis.dis3.jdbc.dao;

import ru.nsu.ccfit.beloglazov.dis.dis3.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis3.generated.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagDao extends Dao {

    private final Node node;
    private final Tag tag;

    public TagDao(Connection connection, Node node, Tag tag) {
        super(connection);
        this.node = node;
        this.tag = tag;
    }

    @Override
    public String getInsertStatementSql() {
        return "insert into tags (" +
                "node_id, k, v" +
                ") values (" +
                node.getId() + ", '" +
                tag.getK().replace('\'', '\\') + "', '" +
                tag.getV().replace('\'', '\\') + "')";
    }

    @Override
    public PreparedStatement getPreparedStatement() throws SQLException {
        PreparedStatement ps =  connection.prepareStatement(getEmptyInsertStatementSql());
        ps.setInt(1, node.getId().intValue());
        ps.setString(2, tag.getK());
        ps.setString(3, tag.getV());
        return ps;
    }

    @Override
    protected String getEmptyInsertStatementSql() {
        return "insert into tags (" +
                "node_id, k, v" +
                ") values (?, ?, ?)";
    }
}