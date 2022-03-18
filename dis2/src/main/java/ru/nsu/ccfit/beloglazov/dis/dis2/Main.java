package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        new CompressedOsmParser().parse("RU-NVS.osm.bz2");

        try {
            DatabaseInitializer.initialize();
        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }

        log.info("Successfully connected to local database");
    }
}