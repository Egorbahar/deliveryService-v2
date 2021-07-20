package com.exposit.dao;

import com.exposit.model.Store;

import java.io.IOException;
import java.util.List;

public interface StoreDao {
    void save(Store store) throws IOException;

    Store getById(Long id);

    List<Store> getAll();

    void delete(Long id);

    Store update(Store storeUp);
}
