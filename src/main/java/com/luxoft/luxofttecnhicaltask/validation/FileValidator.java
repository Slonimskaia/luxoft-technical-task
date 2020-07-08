package com.luxoft.luxofttecnhicaltask.validation;


import com.opencsv.CSVReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class FileValidator {

    public boolean isCsv(MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        return content.contains(",");
    }

    public boolean lastLineIsEmpty(MultipartFile file, Charset encoding) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             Reader reader = new InputStreamReader(inputStream, encoding);
             Reader buffer = new BufferedReader(reader)) {

            int intRepresentationOfNewLine = ('\n');
            return getLastCharacterFromFile(buffer) == intRepresentationOfNewLine;
        }
    }

    public boolean fileHasValidLines(MultipartFile file, Charset encoding) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             Reader reader = new InputStreamReader(inputStream, encoding);
             CSVReader csvReader = new CSVReader((reader))) {

            return csvReader.readAll().stream()
                    .allMatch(line -> hasFourValues(line) && primaryKeyIsNotEmpty(line));
        }
    }

    public boolean fileHasValidHeader(MultipartFile file, Charset encoding) throws IOException {
        int firstLineInFile = 0;
        try (InputStream inputStream = file.getInputStream();
             Reader reader = new InputStreamReader(inputStream, encoding);
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

    private boolean primaryKeyIsNotEmpty(String[] lineValues) {
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
