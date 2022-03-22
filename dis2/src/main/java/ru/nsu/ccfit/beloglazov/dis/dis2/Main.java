package ru.nsu.ccfit.beloglazov.dis.dis2;

import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseConnection;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseInitializer;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseLoader;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Node> nodes = CompressedOsmParser.parse(
                "RU-NVS.osm.bz2",
                false);

//        DatabaseInitializer.initializeMissingTables(DatabaseConnection.getConnection());
//        DatabaseLoader loader = new DatabaseLoader(DatabaseConnection.getConnection());
//        loader.clearData();
//        loader.insertData(ExecuteStrategy.PREPARED_STATEMENT, nodes);
    }
}