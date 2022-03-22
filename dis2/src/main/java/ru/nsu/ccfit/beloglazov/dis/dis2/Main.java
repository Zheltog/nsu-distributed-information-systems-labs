package ru.nsu.ccfit.beloglazov.dis.dis2;

import static ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy.STATEMENT;

public class Main {

    public static void main(String[] args) {

        CompressedOsmParser.parseWithLoading(
                STATEMENT,
                "RU-NVS.osm.bz2",
                false);

    }
}