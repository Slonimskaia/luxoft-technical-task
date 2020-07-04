package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.dao.FileRepository;
import com.luxoft.luxofttecnhicaltask.model.File;

public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    public FileServiceImpl(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(File file) {
        repository.save(file);
    }
}
