package com.exposit.core.dao;

import com.exposit.persistence.entity.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product product);

    Product getById(Long id);

    List<Product> getAll();

    void delete(Long id);

    Product update(Product productUp);
}
