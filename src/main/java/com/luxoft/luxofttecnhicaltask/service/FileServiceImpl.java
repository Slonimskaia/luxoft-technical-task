package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.dao.FileRepository;
import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @Override
    public List<String> getAllNames() {
        return getAll().stream().map(CsvFile::getFileName).collect(toList());
    }

    private List<CsvFile> getAll() {
        return (List<CsvFile>) repository.findAll();
    }


}
