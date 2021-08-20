package com.exposit.core.service;

import com.exposit.persistence.entity.Store;

import java.util.List;


public interface StoreService {
    void add(Store store);

    void delete(Long id);

    Store update(Store store);

    List<Store> getAll();

    Store findById(Long id);
}
