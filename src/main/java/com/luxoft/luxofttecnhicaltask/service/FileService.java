package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.model.CsvFile;

import java.util.List;
import java.util.Optional;

public interface FileService {

    void add(CsvFile csvFile);

    Optional<CsvFile> get(String name);

    List<String> getAllNames();
}
