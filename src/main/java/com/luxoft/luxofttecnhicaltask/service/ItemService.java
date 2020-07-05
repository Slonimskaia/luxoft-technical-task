package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.model.Item;

import java.util.List;

public interface ItemService {

    void add(Item item);

    void delete(Item item);

    List<Item> getAllFileItems(String fileName);

    Item getByPrimaryKey(String primaryKey, String fileName);
}
