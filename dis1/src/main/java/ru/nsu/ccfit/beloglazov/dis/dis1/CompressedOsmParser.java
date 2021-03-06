package ru.nsu.ccfit.beloglazov.dis.dis1;

import org.apache.log4j.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;

public class CompressedOsmParser {

    private static final Logger log = Logger.getLogger(CompressedOsmParser.class);

    public void parse(String fileName) {
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
            process(br);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void process(BZ2BufferedReader br) throws XMLStreamException {
        log.info("Processing file...");
        try (StAXStreamProcessor processor = new StAXStreamProcessor(br)) {
            log.info("StAX processor for file created successfully...");
            log.info("Going through XML...");
            XMLEventReader eventReader = processor.getReader();
            Map<String, Integer> redactions = new HashMap<>();
            Map<String, Integer> tagKeys = new HashMap<>();
            while (eventReader.hasNext()) {
                XMLEvent nextEvent = eventReader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("node")) {
                        processElementForRedactionsMap(redactions, startElement);
                        processElementForTagKeysMap(tagKeys, startElement);
                    }
                }
            }
            log.info("Processing is finished...");
            log.info("Printing result for redactions...");
            printMap(sortByValues(redactions));
            log.info("Printing result for 'node' tag keys...");
            printMap(sortByValues(tagKeys));
        }
    }

    private InputStream getInputStreamForResource(String fileName) throws FileNotFoundException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException("File " + fileName + " does not exist in resources folder");
        } else {
            return is;
        }
    }

    private void processElementForTagKeysMap(Map<String, Integer> tagKeys, StartElement element) {
        Iterator<Attribute> attributes = element.getAttributes();
        while (attributes.hasNext()) {
            String key = attributes.next().getName().toString();
            tagKeys.merge(key, 1, Integer::sum);
        }
    }

    private void processElementForRedactionsMap(Map<String, Integer> redactions, StartElement element) {
        String user = element.getAttributeByName(new QName("user")).getValue();
        redactions.merge(user, 1, Integer::sum);
    }

    private Map<String, Integer> sortByValues(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((Entry.<String, Integer>comparingByValue().reversed()))
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            log.info(key + " - " + map.get(key));
        }
    }
}