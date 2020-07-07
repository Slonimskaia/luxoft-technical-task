package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.model.CsvFile;

import java.util.List;

public interface FileService {

    void add(CsvFile csvFile);

    List<String> getAllNames();

    boolean isFileNameUnique(String fileName);
}
