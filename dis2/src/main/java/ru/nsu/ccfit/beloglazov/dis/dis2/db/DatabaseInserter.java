package ru.nsu.ccfit.beloglazov.dis.dis2.db;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import java.sql.Connection;
import java.util.List;

public class DatabaseInserter {

    private static final Logger log = Logger.getLogger(DatabaseInserter.class);

    private final Connection connection;

    public DatabaseInserter(Connection connection) {
        this.connection = connection;
    }

    public void insertNodes(List<Node> nodes) {
        // TODO
    }

    public void insertTags(List<Tag> tags) {
        // TODO
    }
}