package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.NodeDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.TagDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseLoader {

    private static final Logger log = Logger.getLogger(DatabaseLoader.class);

    private final Connection connection;
    private Statement batch = null;

    public DatabaseLoader(Connection connection) {
        this.connection = connection;
    }

    public long insertViaStatement(Node node) {
        try {
            long startTime = System.nanoTime();
            NodeDao nodeDao = new NodeDao(connection, node);
            connection.createStatement().execute(nodeDao.getInsertStatementSql());
            for (Tag tag : node.getTag()) {
                TagDao tagDao = new TagDao(connection, node, tag);
                connection.createStatement().execute(tagDao.getInsertStatementSql());
            }
            return System.nanoTime() - startTime;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    public long insertViaPreparedStatement(Node node) {
        try {
            long startTime = System.nanoTime();
            NodeDao nodeDao = new NodeDao(connection, node);
            nodeDao.getPreparedStatement().execute();
            for (Tag tag : node.getTag()) {
                TagDao tagDao = new TagDao(connection, node, tag);
                tagDao.getPreparedStatement().execute();
            }
            return System.nanoTime() - startTime;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    public void addToBatch(Node node) {
        try {
            if (batch == null) batch = connection.createStatement();
            NodeDao nodeDao = new NodeDao(connection, node);
            batch.addBatch(nodeDao.getInsertStatementSql());
            for (Tag tag : node.getTag()) {
                TagDao tagDao = new TagDao(connection, node, tag);
                batch.addBatch(tagDao.getInsertStatementSql());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public long executeBatch() {
        try {
            long startTime = System.nanoTime();
            batch.executeBatch();
            return System.nanoTime() - startTime;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return -1;
        }
    }
}