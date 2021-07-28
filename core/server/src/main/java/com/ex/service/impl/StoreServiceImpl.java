package com.ex.service.impl;

import com.ex.dao.StoreDao;
import com.ex.exception.FormatFileException;
import com.ex.model.Store;
import com.ex.service.StoreService;

import java.io.IOException;
import java.util.List;


public class StoreServiceImpl implements StoreService {
    private final StoreDao storeDao;

    public StoreServiceImpl(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public void add(Store store) throws IOException, FormatFileException {
        storeDao.save(store);
    }

    @Override
    public void deleteStore(Long id) throws FormatFileException, IOException {
        storeDao.delete(id);
    }

    @Override
    public void updateStore(Store store) throws FormatFileException, IOException {
        storeDao.update(store);
    }

    public List<Store> getAll() throws FormatFileException, IOException {
        return storeDao.getAll();
    }

    @Override
    public Store getById(Long id) throws FormatFileException, IOException {
        return storeDao.getById(id);
    }
}
