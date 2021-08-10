package com.exposit.core.service;

import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;

import java.util.List;
import java.util.Map;


public interface ProductService {
    Product addProduct(Product product);

    List<Product> sortByPrice(Long storeId);

    Product delete(Long id);

    void updateProduct(Product productUp);

    List<Product> findByAttributes(Map<Integer, String> attributes);

    List<Product> findByCategory(Category category);

    List<Product> getAll();

    List<Product> getAllByStoreId(Long storeId);

    Product getById(Long id);

    void deleteProductByStoreId(Long id);

    List<Product> findByCategories(List<Integer> categories);
}
