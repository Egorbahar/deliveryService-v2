package com.exposit.core.service;

import com.exposit.persistence.entity.Store;

import java.util.List;


public interface StoreService extends DefaultService{
    void save(Store store);

    void delete(Long id);

    Store update(Store store);

    List<Store> findAll();

    Store findById(Long id);

    List<Store> findByProductNameWithMinProductPrice(String productName);

    List<Store>  findAllStoresWhereProductIsInStock(String productName);

    List<Store> filterByNameOrAddress(String name, String address);
}
