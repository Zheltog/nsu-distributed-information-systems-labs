package ru.nsu.ccfit.beloglazov.dis.dis1;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CompressedOsmParser {

    private static final Logger log = Logger.getLogger(CompressedOsmParser.class);

    public void parse(String fileName) throws FileNotFoundException {
        log.info("Starting...");
        InputStream is = getInputStreamForResource(fileName);
        try (BZ2BufferedReader reader = new BZ2BufferedReader(is)) {
            log.info("Reader for file created successfully...");
            String line;
            for (int i = 0; i < 5; i++) {
                line = reader.readLine();
                System.out.println(line + "\n");
            }
        } catch (CompressorException | IOException e) {
            log.error(e.getMessage());
        }
    }

    private InputStream getInputStreamForResource(String fileName) throws FileNotFoundException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            String message = "File " + fileName + " does not exist in resources folder";
            log.error(message);
            throw new FileNotFoundException(message);
        } else {
            log.info("File " + fileName + " found in resources folder...");
            return is;
        }
    }
}