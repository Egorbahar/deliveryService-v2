package com.exposit.core.dao;

import com.exposit.persistence.entity.Store;

import java.util.List;

public interface StoreDao {
    void save(Store store);

    Store getById(Long id);

    List<Store> getAll();

    void delete(Long id);

    Store update(Store storeUp);
}
