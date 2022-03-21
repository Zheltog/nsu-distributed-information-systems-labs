package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseConnection;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseInitializer;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseInserter;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Map<String, List> result = new CompressedOsmParser().parse("RU-NVS.osm.bz2");
            new DatabaseInitializer(DatabaseConnection.getConnection()).initializeMissingTables();
            DatabaseInserter inserter = new DatabaseInserter(DatabaseConnection.getConnection());
            inserter.insertNodes(result.get("nodes"));
            inserter.insertTags(result.get("tags"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}