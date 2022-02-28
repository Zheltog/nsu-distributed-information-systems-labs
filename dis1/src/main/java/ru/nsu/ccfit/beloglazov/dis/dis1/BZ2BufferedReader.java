package ru.nsu.ccfit.beloglazov.dis.dis1;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import java.io.*;

public class BZ2BufferedReader extends BufferedReader {

    public BZ2BufferedReader(InputStream is) throws CompressorException {
        super(new InputStreamReader(new CompressorStreamFactory().createCompressorInputStream(new BufferedInputStream(is))));
    }
}