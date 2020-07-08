package com.luxoft.luxofttecnhicaltask.validation;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.nio.charset.Charset.defaultCharset;
import static org.junit.Assert.*;

public class FileValidatorTest {

    @Test
    public void fileHeaderIsInvalid() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("invalid-header.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/invalid-header.txt")));

        assertEquals(FALSE, fileValidator.fileHasValidHeader(file, defaultCharset()));
    }

    @Test
    public void fileHeaderIsValid() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("valid-file.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/valid-file.txt")));

        assertEquals(TRUE, fileValidator.fileHasValidHeader(file, defaultCharset()));
    }

    @Test
    public void notEachFileLineHasFourValues() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("invalid-number-of-elements-in-row.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/invalid-number-of-elements-in-row.txt")));

        assertEquals(FALSE, fileValidator.fileHasValidLines(file, defaultCharset()));
    }

    @Test
    public void eachFileLineHasFourValues() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("valid-file.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/valid-file.txt")));

        assertEquals(TRUE, fileValidator.fileHasValidLines(file, defaultCharset()));
    }

    @Test
    public void primaryKeyIsNotValid() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("invalid-empty-primary-key.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/invalid-empty-primary-key.txt")));

        assertEquals(FALSE, fileValidator.fileHasValidLines(file, defaultCharset()));
    }

    @Test
    public void primaryKeyIsValid() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("valid-file.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/valid-file.txt")));

        assertEquals(TRUE, fileValidator.fileHasValidLines(file, defaultCharset()));
    }

    @Test
    public void lastLineIsNotEmpty() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("absence-of-empty-line.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/absence-of-empty-line.txt")));

        assertEquals(FALSE, fileValidator.lastLineIsEmpty(file, defaultCharset()));
    }

    @Test
    public void lastLineIsEmpty() throws IOException {
        FileValidator fileValidator = new FileValidator();
        MultipartFile file = new MockMultipartFile("valid-file.txt",
                new FileInputStream(new File("src/main/resources/files-for-tests/valid-file.txt")));

        assertEquals(TRUE, fileValidator.lastLineIsEmpty(file, defaultCharset()));
    }
}