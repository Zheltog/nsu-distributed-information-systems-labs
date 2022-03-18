package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;
import java.util.List;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        new CompressedOsmParser().parse("RU-NVS.osm.bz2");

        try {
            DatabaseInitializer dt = new DatabaseInitializer();
            log.info("Successfully connected to local database");
            List<String> tables = dt.getTables();
            log.info("Tables in database: " + dt.getTables());
            if (!tables.contains("nodes")) {
                dt.createNodesTable();
                log.info("Created table 'nodes'");
            }
            if (!tables.contains("tags")) {
                dt.createTagsTable();
                log.info("Created table 'tags'");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}