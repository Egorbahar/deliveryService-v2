package com.exposit.dao;

import com.exposit.model.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();

    Product delete(Product product);

    void deleteAllByStoreId(Long id);

    Product update(Product productUp);

    List<Product> getAllByStoreId(Long storeId);
}
