package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseLoader;
import ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;
import static ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseCleaner.cleanTables;
import static ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseConnection.getConnection;
import static ru.nsu.ccfit.beloglazov.dis.dis2.db.DatabaseInitializer.initializeMissingTables;
import static ru.nsu.ccfit.beloglazov.dis.dis2.db.ExecuteStrategy.BATCH;

public class CompressedOsmParser {

    private static final Logger log = Logger.getLogger(CompressedOsmParser.class);

    private static ExecuteStrategy strategy;
    private static DatabaseLoader loader;
    private static int for1st;

    /**
        @param es execute strategy for data loading
        @param fileName name of compressed osm file
        @param printStatistics should print redactions and names statistics after finishing (true/false)
        @param forFirst process for first ? nodes (-1 for all)
     **/
    public static void parseWithLoading(
            ExecuteStrategy es,
            String fileName,
            boolean printStatistics,
            int forFirst
    ) {
//        log.info("Starting...");
        initializeMissingTables(getConnection());
        cleanTables(getConnection());
        strategy = es;
        for1st = forFirst;
        loader = new DatabaseLoader(getConnection());
//        log.info("Creating reader for file...");
        try (BZ2BufferedReader br = new BZ2BufferedReader(getInputStreamForResource(fileName))) {
//            log.info("Buffered reader for file created successfully...");
            long bestInsertTimeNSecs = process(br, printStatistics);
            double bestInsertTimeSecs = (double) bestInsertTimeNSecs / 1000000000;
            log.info("Best 1 node insert time (sec) for strategy " + strategy + ": " + bestInsertTimeSecs);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static long process(BZ2BufferedReader br, boolean printStatistics) throws XMLStreamException, JAXBException {
//        log.info("Processing file...");
        try (OSMReader reader = new OSMReader(br)) {
//            log.info("StAX processor for file created successfully...");
//            log.info("Going through XML...");

            Map<String, Integer> redactions = new HashMap<>();
            Map<String, Integer> names = new HashMap<>();

            int nodesCount = 1;
            long bestInsertTime = Long.MAX_VALUE;

            while (true) {
                Node node = reader.nextNode();
                if (node == null) {
                    break;
                }

                switch (strategy) {
                    case STATEMENT:
                        long timeS = loader.insertViaStatement(node);
                        if (timeS < bestInsertTime) bestInsertTime = timeS;
                        break;
                    case PREPARED_STATEMENT:
                        long timePS = loader.insertViaPreparedStatement(node);
                        if (timePS < bestInsertTime) bestInsertTime = timePS;
                        break;
                    case BATCH:
                        loader.addToBatch(node);
                }

                if (printStatistics) {
                    processElementForRedactionsMap(redactions, node);
                    for (Tag tag : node.getTag()) {
                        processElementForNamesMap(names, tag);
                    }
                }

                if (++nodesCount == for1st) {
                    break;
                }
            }

            if (strategy.equals(BATCH)) {
                long timeB = loader.executeBatch() / nodesCount;
                if (timeB < bestInsertTime) bestInsertTime = timeB;
            }

//            log.info("Processing is finished...");

            if (printStatistics) {
                log.info("Printing result for redactions...");
                printMap(sortByValues(redactions));
                log.info("Printing result for names...");
                printMap(sortByValues(names));
            }

            return bestInsertTime;
        }
    }

    private static InputStream getInputStreamForResource(String fileName) throws FileNotFoundException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException("File " + fileName + " does not exist in resources folder");
        } else {
            return is;
        }
    }

    private static void processElementForNamesMap(Map<String, Integer> names, Tag tag) {
        if (tag.getK().equals("name")) {
            names.merge(tag.getV(), 1, Integer::sum);
        }
    }

    private static void processElementForRedactionsMap(Map<String, Integer> redactions, Node node) {
        redactions.merge(node.getUser(), 1, Integer::sum);
    }

    private static Map<String, Integer> sortByValues(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((Entry.<String, Integer>comparingByValue().reversed()))
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            log.info(key + " - " + map.get(key));
        }
    }
}