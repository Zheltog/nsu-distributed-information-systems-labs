package ru.nsu.ccfit.beloglazov.dis.dis1;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class StAXStreamProcessor implements AutoCloseable {

    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLEventReader reader;

    public StAXStreamProcessor(BZ2BufferedReader br) throws XMLStreamException {
        reader = FACTORY.createXMLEventReader(br);
    }

    public XMLEventReader getReader() {
        return reader;
    }

    @Override
    public void close() throws XMLStreamException {
        if (reader != null) {
            reader.close();
        }
    }
}