package ru.nsu.ccfit.beloglazov.dis.dis2;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Node;
import ru.nsu.ccfit.beloglazov.dis.dis2.generated.Tag;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;

public class CompressedOsmParser {

    private static final Logger log = Logger.getLogger(CompressedOsmParser.class);

    public static List<Node> parse(String fileName, boolean printStatistics) {
        log.info("Starting...");
        log.info("Creating reader for file...");
        InputStream is = null;
        try {
             is = getInputStreamForResource(fileName);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
        log.info("File " + fileName + " found in resources folder...");
        try (BZ2BufferedReader br = new BZ2BufferedReader(is)) {
            log.info("Buffered reader for file created successfully...");
            return process(br, printStatistics);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private static List<Node> process(BZ2BufferedReader br, boolean printStatistics) throws XMLStreamException, JAXBException {
        log.info("Processing file...");
        try (OSMReader reader = new OSMReader(br)) {
            log.info("StAX processor for file created successfully...");
            log.info("Going through XML...");

            List<Node> nodes = new LinkedList<>();
            Map<String, Integer> redactions = new HashMap<>();
            Map<String, Integer> names = new HashMap<>();

            while (true) {
                Node node = reader.nextNode();
                if (node == null) {
                    break;
                }
                nodes.add(node);

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

            return nodes;
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