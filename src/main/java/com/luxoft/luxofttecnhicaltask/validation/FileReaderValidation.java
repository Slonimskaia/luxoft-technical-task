package com.luxoft.luxofttecnhicaltask.validation;


import java.io.*;
import java.nio.charset.Charset;

public class FileReaderValidation {

    private final int endOfTheStream = -1;
    private final int intRepresentationOfNewLine = (int) ('\n');

    public boolean doesFileHasLastEmptyLine(File file, Charset encoding) throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, encoding);
             Reader buffer = new BufferedReader(reader)) {
            return getLastCharacterFromFile(buffer) == intRepresentationOfNewLine;
        }
    }

    private int getLastCharacterFromFile(Reader reader) throws IOException {
        int nextChar;
        int lastChar = 0;
        while ((nextChar = reader.read()) != endOfTheStream) {
            lastChar = nextChar;
        }
        return lastChar;
    }
}
