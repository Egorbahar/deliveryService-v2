package com.exposit.core.service;

import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;

import java.util.List;
import java.util.Map;


public interface ProductService extends DefaultService{
    Product save(Product product);

    List<Product> sortByPrice(Long storeId);

    void delete(Long id);

    Product update(Product product);

    List<Product> findByAttributes(Map<Integer, String> attributes);

    List<Product> findByCategory(Category category);

    List<Product> getAll();

    List<Product> getAllByStoreId(Long storeId);

    Product findById(Long id);

    void deleteProductByStoreId(Long id);

    List<Product> findByCategories(List<Integer> categories);

}
