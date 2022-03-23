package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.NodeDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.TagDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseLoader {

    private static final Logger log = Logger.getLogger(DatabaseLoader.class);

    private final NodeDao nodeDao;
    private final TagDao tagDao;

    public DatabaseLoader(Connection connection) {
        nodeDao = new NodeDao(connection);
        tagDao = new TagDao(connection);
    }

    public void insertNode(ExecuteStrategy es, Node node) {
        try {
            nodeDao.insert(es, node);
            for (Tag tag : node.getTag()) {
                tagDao.insert(es, node, tag);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}