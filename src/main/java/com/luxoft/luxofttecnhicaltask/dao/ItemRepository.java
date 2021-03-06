package com.luxoft.luxofttecnhicaltask.dao;

import com.luxoft.luxofttecnhicaltask.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query(value = "SELECT * FROM ITEMS INNER JOIN CSV_FILES ON CSV_FILES.ID = ITEMS.FILE_ID " +
            "WHERE CSV_FILES.NAME = :fileName", nativeQuery = true)
    List<Item> getFileItems(@Param("fileName") String fileName);

    @Query(value = "SELECT * FROM ITEMS INNER JOIN CSV_FILES ON CSV_FILES.ID = ITEMS.FILE_ID " +
            "WHERE ITEMS.PRIMARY_KEY = :primaryKey AND CSV_FILES.NAME = :fileName", nativeQuery = true)
    Item getByPrimaryKey(@Param("primaryKey") String primaryKey, @Param("fileName") String fileName);
}
