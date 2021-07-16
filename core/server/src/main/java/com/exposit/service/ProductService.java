package com.exposit.service;

import com.exposit.model.Category;
import com.exposit.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> sortByPrice(Long storeId);

    Product deleteProduct(Product product);

    void updateProduct(Product productUp);

    List<Product> findByAttributes(Map<Integer, String> attributes);

    List<Product> findByCategory(Category category);

    List<Product> getAll();

    List<Product> getAllByStoreId(Long storeId);

    Product getById(Long id);

    void deleteProductByStoreId(Long id);

    List<Product> findByCategories(List<Integer> categories);
}
