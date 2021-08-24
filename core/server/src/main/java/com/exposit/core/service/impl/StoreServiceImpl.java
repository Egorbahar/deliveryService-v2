package com.exposit.core.service.impl;

import com.exposit.core.dao.StoreDao;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;

import java.util.List;


public class StoreServiceImpl implements StoreService {
    private final StoreDao storeDao;

    public StoreServiceImpl(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public void save(Store store){
        storeDao.save(store);
    }

    @Override
    public void delete(Long id){
        storeDao.delete(id);
    }

    @Override
    public Store update(Store store){
        return storeDao.update(store);
    }

    public List<Store> getAll(){
        return storeDao.getAll();
    }

    @Override
    public Store findById(Long id){
        return storeDao.getById(id);
    }
}
