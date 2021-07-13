package com.exposit.service;

import com.exposit.model.Store;

import java.io.IOException;
import java.util.List;

public interface StoreService {
    void add(Store store) throws IOException;

    void deleteStore(Long id);

    void updateStore(Store store);

    List<Store> getAll();

    Store getById(Long id);
}
