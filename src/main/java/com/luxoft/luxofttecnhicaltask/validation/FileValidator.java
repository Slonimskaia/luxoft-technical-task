package com.luxoft.luxofttecnhicaltask.validation;


import com.luxoft.luxofttecnhicaltask.Column;
import com.opencsv.CSVReader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class FileValidator {

    public boolean lastLineIsEmpty(File file, Charset encoding) throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, encoding);
             Reader buffer = new BufferedReader(reader)) {

            int intRepresentationOfNewLine = (int) ('\n');
            return getLastCharacterFromFile(buffer) == intRepresentationOfNewLine;
        }
    }

    public boolean documentHasValidLines(String filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVReader csvReader = new CSVReader((reader))) {

            return csvReader.readAll().stream()
                    .allMatch(line -> hasFourValues(line) && firstValueIsNotEmpty(line));
        }
    }

    public boolean fileHasValidHeader(String filePath) throws IOException {
        int firstLineInFile = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVReader csvReader = new CSVReader((reader))) {

            String[] firstLine = csvReader.readAll().get(firstLineInFile);
            return headerIsValid(firstLine);
        }
    }

    private boolean headerIsValid(String[] firstLine) {
        if (firstLine.length == 4) {
            for (int i=0; i<firstLine.length; i++) {
                if (!firstLine[i].equals(Column.values()[i].toString())) {
                    return FALSE;
                }
            }
            return TRUE;
        } else {
            return FALSE;
        }
    }

    private boolean hasFourValues(String[] lineValues) {
        int numberOfValues = 4;
        return lineValues.length == numberOfValues;
    }

    private boolean firstValueIsNotEmpty(String[] lineValues) {
        int firstValue = 0;
        return isNotBlank(lineValues[firstValue]);
    }

    private int getLastCharacterFromFile(Reader reader) throws IOException {
        int endOfTheStream = -1;
        int nextChar;
        int lastChar = 0;
        while ((nextChar = reader.read()) != endOfTheStream) {
            lastChar = nextChar;
        }
        return lastChar;
    }
}
