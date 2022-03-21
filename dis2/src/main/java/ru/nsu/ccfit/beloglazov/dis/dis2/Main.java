package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseConnection;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseInitializer;
import java.sql.Connection;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        new CompressedOsmParser().parse("RU-NVS.osm.bz2");

        try {
            Connection connection = DatabaseConnection.getConnection();
            DatabaseInitializer dt = new DatabaseInitializer(connection);
            dt.initializeMissingTables();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}