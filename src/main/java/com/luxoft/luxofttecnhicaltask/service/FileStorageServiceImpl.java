package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import com.luxoft.luxofttecnhicaltask.model.Item;
import com.luxoft.luxofttecnhicaltask.storage.StorageProperties;
import com.luxoft.luxofttecnhicaltask.storage.exception.StorageException;
import com.luxoft.luxofttecnhicaltask.storage.exception.StorageFileNotFoundException;
import com.luxoft.luxofttecnhicaltask.validation.FileValidator;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.luxoft.luxofttecnhicaltask.validation.Column.*;
import static org.springframework.util.StringUtils.cleanPath;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation;
    private FileValidator validator;

    @Autowired
    private FileService fileService;

    @Autowired
    private ItemService itemService;

    @Autowired
    public FileStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getFilesStorage());
        validator = new FileValidator();
    }

    @Override
    public void store(MultipartFile file) {
        String filename = cleanPath(file.getOriginalFilename());
        try {
            if (!isFileValid(file)) {
                throw new IOException();
            }
            try (InputStream inputStream = file.getInputStream();
                 Reader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
                 CSVReader csvReader = new CSVReader((reader))) {
                CsvFile csvFile = new CsvFile(filename);
                fileService.add(csvFile);
                List<String[]> allLines = csvReader.readAll();
                int firstFileRow = 1;
                allLines.subList(firstFileRow, allLines.size()).forEach(i -> itemService.add(getItem(i, csvFile)));
            }
        } catch (IOException e) {
            throw new SecurityException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    private boolean isFileValid(MultipartFile file) throws IOException {
        return validator.lastLineIsEmpty(file, Charset.defaultCharset()) &&
                validator.documentHasValidLines(file, Charset.defaultCharset()) &&
                validator.fileHasValidHeader(file, Charset.defaultCharset());
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
}
