package com.exposit.core.dao;

import com.exposit.core.model.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();

    Product delete(Long id);

    void deleteAllByStoreId(Long id);

    Product update(Product productUp);

    List<Product> getAllByStoreId(Long storeId);
}
