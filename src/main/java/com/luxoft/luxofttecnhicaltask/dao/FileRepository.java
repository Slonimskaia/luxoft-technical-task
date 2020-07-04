package com.luxoft.luxofttecnhicaltask.dao;

import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<CsvFile, Integer> {
}
