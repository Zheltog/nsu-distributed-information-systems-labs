package ru.nsu.ccfit.beloglazov.dis.dis2;

import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;

public class Main {

    public static void main(String[] args) {

        for (ExecuteStrategy es : ExecuteStrategy.values()) {
            CompressedOsmParser.parseWithLoading(
                    es,
                    "RU-NVS.osm.bz2",
                    false,
                    30000
            );
        }
    }
}