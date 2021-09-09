package com.exposit.core.service.implfile;

import com.exposit.core.dao.StoreDao;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;

import java.util.List;
import java.util.stream.Collectors;


public class StoreFileService implements StoreService {
    private final StoreDao storeDao;

    public StoreFileService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public void save(Store store) {
        storeDao.save(store);
    }

    @Override
    public void delete(Long id) {
        storeDao.delete(id);
    }

    @Override
    public Store update(Store store) {
        return storeDao.update(store);
    }

    public List<Store> findAll() {
        return storeDao.getAll();
    }

    @Override
    public Store findById(Long id) {
        return storeDao.getById(id);
    }

    @Override
    public List<Store> findByProductNameWithMinProductPrice(String productName) {
        return storeDao.getAll();
    }

    @Override
    public List<Store> findAllStoresWhereProductIsInStock(String productName) {
        return null;
    }

    @Override
    public List<Store> filterByNameOrAddress(String name, String address) {
        return storeDao.getAll().stream()
                .filter(s->s.getName().equals(name)||s.getAddress().equals(address))
                .collect(Collectors.toList());
    }

}
