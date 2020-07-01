package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.springframework.util.StringUtils.cleanPath;

public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getFilesStorage());
    }

    @Override
    public void store(MultipartFile file) {
        String filename = cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new SecurityException("Failed to store file " + filename, e);
        }
    }
}
