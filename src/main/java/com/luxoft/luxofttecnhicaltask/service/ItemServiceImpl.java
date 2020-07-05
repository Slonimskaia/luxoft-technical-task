package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.dao.ItemRepository;
import com.luxoft.luxofttecnhicaltask.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Item item) {
        repository.save(item);
    }

    @Override
    public List<Item> getAllFileItems(String fileName) {
        return repository.getFileItems(fileName);
    }

    @Override
    public Item getByPrimaryKey(String primaryKey, String fileName){
        return repository.getByPrimaryKey(primaryKey, fileName);
    }

    @Override
    public void deleteByPrimaryKey(String primaryKey, String fileName){
        repository.deleteByPrimaryKey(primaryKey, fileName);
    }
}
