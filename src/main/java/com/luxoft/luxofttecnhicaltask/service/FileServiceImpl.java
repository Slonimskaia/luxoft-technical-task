package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.dao.FileRepository;
import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    public FileServiceImpl(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(CsvFile csvFile) {
        repository.save(csvFile);
    }
}
