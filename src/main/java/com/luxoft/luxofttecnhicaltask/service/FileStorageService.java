package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.model.Item;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {

    void store(MultipartFile file);

    List<String> getAllFilesNames();

    Path load(String filename);

    Resource loadAsResource(String filename);

    List<Item> getCsvFile(String fileName);

    Item getByPrimaryKey(String primaryKey, String fileName);

    void deleteByPrimaryKey(String primaryKey, String fileName);
}
