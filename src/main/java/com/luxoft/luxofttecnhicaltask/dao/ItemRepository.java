package com.luxoft.luxofttecnhicaltask.dao;

import com.luxoft.luxofttecnhicaltask.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
}
