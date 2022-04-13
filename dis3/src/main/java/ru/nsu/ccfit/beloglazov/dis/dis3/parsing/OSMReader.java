package ru.nsu.ccfit.beloglazov.dis.dis3.parsing;

import ru.nsu.ccfit.beloglazov.dis.dis3.generated.Node;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class OSMReader implements AutoCloseable {

    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLEventReader reader;
    private final Unmarshaller nodeUnmarshaller;

    public OSMReader(BZ2BufferedReader br) throws XMLStreamException, JAXBException {
        reader = FACTORY.createXMLEventReader(br);
        nodeUnmarshaller = JAXBContext.newInstance(Node.class).createUnmarshaller();
    }

    public Node nextNode() throws XMLStreamException, JAXBException {
        while (true) {
            if (!reader.hasNext()) {
                return null;
            }
            XMLEvent nextEvent = reader.peek();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("node")) {
                    return (Node) nodeUnmarshaller.unmarshal(reader);
                }
            }
            reader.nextEvent();
        }
    }

    @Override
    public void close() throws XMLStreamException {
        if (reader != null) {
            reader.close();
        }
    }
}