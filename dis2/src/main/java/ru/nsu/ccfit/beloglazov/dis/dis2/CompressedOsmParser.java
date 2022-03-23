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

public class CompressedOsmParser {

    private static final Logger log = Logger.getLogger(CompressedOsmParser.class);

    private static ExecuteStrategy strategy;
    private static DatabaseLoader loader;

    public static void parseWithLoading(
            ExecuteStrategy es,
            String fileName,
            boolean printStatistics
    ) {
        long startTime = System.currentTimeMillis();
        initializeMissingTables(getConnection());
        cleanTables(getConnection());
        log.info("Starting...");
        strategy = es;
        loader = new DatabaseLoader(getConnection());
        log.info("Creating reader for file...");
        try (BZ2BufferedReader br = new BZ2BufferedReader(getInputStreamForResource(fileName))) {
            log.info("Buffered reader for file created successfully...");
            process(br, printStatistics);
            log.info("Time (ms): " + (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static void process(BZ2BufferedReader br, boolean printStatistics) throws XMLStreamException, JAXBException {
        log.info("Processing file...");
        try (OSMReader reader = new OSMReader(br)) {
            log.info("StAX processor for file created successfully...");
            log.info("Going through XML...");

            Map<String, Integer> redactions = new HashMap<>();
            Map<String, Integer> names = new HashMap<>();

            while (true) {
                Node node = reader.nextNode();
                if (node == null) {
                    break;
                }
                loader.insertNode(strategy, node);
                if (true) break;

                if (printStatistics) {
                    processElementForRedactionsMap(redactions, node);
                    for (Tag tag : node.getTag()) {
                        processElementForNamesMap(names, tag);
                    }
                }
            }

            log.info("Processing is finished...");

            if (printStatistics) {
                log.info("Printing result for redactions...");
                printMap(sortByValues(redactions));
                log.info("Printing result for names...");
                printMap(sortByValues(names));
            }
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