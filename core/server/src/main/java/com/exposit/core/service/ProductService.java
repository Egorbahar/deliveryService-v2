package com.exposit.core.service;

import com.exposit.persistence.entity.Product;

import java.util.List;


public interface ProductService extends DefaultService {
    Product save(Product product);

    void delete(Long id);

    Product update(Product product);

    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> getAll();

    Product findById(Long id);

    List<Product> findProductByCategoryIdWithPriceLessAvg(Long categoryId);

    List<Product> findProductInStock(boolean param);
}
