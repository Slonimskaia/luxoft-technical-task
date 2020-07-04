package com.luxoft.luxofttecnhicaltask.service;

import com.luxoft.luxofttecnhicaltask.dao.ItemRepository;
import com.luxoft.luxofttecnhicaltask.model.Item;
import org.springframework.stereotype.Service;

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
    public void delete(Item item) {
        repository.delete(item);
    }
}
