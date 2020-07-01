package com.luxoft.luxofttecnhicaltask.service;


import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();
}
