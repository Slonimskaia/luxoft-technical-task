package com.luxoft.luxofttecnhicaltask.dao;

import com.luxoft.luxofttecnhicaltask.model.CsvFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends CrudRepository<CsvFile, Integer> {

    @Query(value="SELECT * FROM CSV_FILES WHERE NAME = :fileName", nativeQuery = true)
    CsvFile isFileNameUnique(@Param("fileName") String fileName);
}
