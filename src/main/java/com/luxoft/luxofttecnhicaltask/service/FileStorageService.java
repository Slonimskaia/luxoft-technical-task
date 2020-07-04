package com.luxoft.luxofttecnhicaltask.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {

    void store(MultipartFile file);

    List<String> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);
}
