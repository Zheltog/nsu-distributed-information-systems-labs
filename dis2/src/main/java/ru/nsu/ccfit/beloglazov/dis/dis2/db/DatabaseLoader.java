package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.NodeDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.dao.TagDao;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseLoader {

    private static final Logger log = Logger.getLogger(DatabaseLoader.class);

    private final NodeDao nodeDao;
    private final TagDao tagDao;

    public DatabaseLoader(Connection connection) {
        nodeDao = new NodeDao(connection);
        tagDao = new TagDao(connection);
    }

    public void clearData() {
        try {
            tagDao.truncate();
            nodeDao.truncate();
            log.info("Tables data cleaning finished successfully");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public void insertData(ExecuteStrategy es, List<Node> nodes) {
        try {
            for (Node node : nodes) {
                nodeDao.insert(es, node);
                for (Tag tag : node.getTag()) {
                    tagDao.insert(es, node, tag);
                }
            }
            log.info("Inserted data via " + es + " strategy");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}