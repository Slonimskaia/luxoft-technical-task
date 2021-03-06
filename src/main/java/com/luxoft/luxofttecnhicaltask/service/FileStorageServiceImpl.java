package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.exception.InvalidFileFormatException;
import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import com.luxoft.luxofttecnhicaltask.model.Item;
import com.luxoft.luxofttecnhicaltask.validation.FileValidator;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

import static com.luxoft.luxofttecnhicaltask.validation.Column.*;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StringUtils.cleanPath;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private FileValidator validator;

    @Autowired
    private FileService fileService;

    @Autowired
    private ItemService itemService;

    @Autowired
    public FileStorageServiceImpl() {
        validator = new FileValidator();
    }

    @Override
    public void store(MultipartFile file) {
        String fileName = cleanPath(file.getOriginalFilename());
        try {
            isFileValid(file);
            try (InputStream inputStream = file.getInputStream();
                 Reader reader = new InputStreamReader(inputStream, defaultCharset());
                 CSVReader csvReader = new CSVReader((reader))) {

                storeFileItems(csvReader, fileName);
            }
        } catch (IOException e) {
            throw new SecurityException("Failed to store file " + fileName, e);
        } catch (InvalidFileFormatException e) {
            throw new SecurityException("Failed to store file " + fileName + ". It should be a text file. " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getAllFilesNames() {
        return fileService.getAllNames();
    }

    @Override
    public List<Item> getCsvFile(String fileName) {
        return itemService.getAllFileItems(fileName);
    }

    @Override
    public Item getByPrimaryKey(String primaryKey, String fileName) {
        return itemService.getByPrimaryKey(primaryKey, fileName);
    }

    @Override
    public void delete(int id) {
        Item item = new Item();
        item.setId(id);
        itemService.delete(item);
    }

    private void isFileValid(MultipartFile file) throws IOException {
        if (fileService.isFileNameUnique(file.getOriginalFilename())) {
            throw new IllegalArgumentException("File with given name has already exist.");
        } else if(!validator.isCsv(file)) {
            throw new InvalidFileFormatException("and should contain comma-separated data.");
        } else if (!validator.fileHasValidLines(file, defaultCharset())) {
            throw new InvalidFileFormatException("Each line in file should have four columns and also first line column should not be empty.");
        } else if (!validator.lastLineIsEmpty(file, defaultCharset())) {
            throw new InvalidFileFormatException("File last line should be empty.");
        } else if (!validator.fileHasValidHeader(file, defaultCharset())) {
            throw new InvalidFileFormatException("File should have header: PRIMARY_KEY, NAME, DESCRIPTION, UPDATED_TIMESTAMP.");
        }
    }

    private Item getItem(String[] itemField, CsvFile csvFile){
        Item item = new Item();
        item.setCsvFileId(csvFile);
        item.setPrimaryKey(itemField[PRIMARY_KEY.getColumn()]);
        item.setName(itemField[NAME.getColumn()]);
        item.setDescription(itemField[DESCRIPTION.getColumn()]);
        item.setUpdatedTimestamp(itemField[UPDATED_TIMESTAMP.getColumn()]);
        return item;
    }

    private void storeFileItems(CSVReader csvReader, String fileName) throws IOException {
        List<String[]> allLines = csvReader.readAll();
        int firstFileRow = 1;
        CsvFile csvFile = storeFile(fileName);
        allLines.subList(firstFileRow, allLines.size()).forEach(i -> itemService.add(getItem(i, csvFile)));
    }

    private CsvFile storeFile(String fileName) {
        CsvFile csvFile = new CsvFile();
        csvFile.setName(fileName);
        fileService.add(csvFile);
        return csvFile;
    }
}
