package com.ex.service;

import com.ex.exception.FormatFileException;
import com.ex.model.Category;
import com.ex.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ProductService {
    Product addProduct(Product product) throws FormatFileException, IOException;

    List<Product> sortByPrice(Long storeId);

    Product deleteProduct(Product product) throws FormatFileException, IOException;

    void updateProduct(Product productUp) throws FormatFileException, IOException;

    List<Product> findByAttributes(Map<Integer, String> attributes) throws FormatFileException, IOException;

    List<Product> findByCategory(Category category) throws FormatFileException, IOException;

    List<Product> getAll() throws FormatFileException, IOException;

    List<Product> getAllByStoreId(Long storeId) throws FormatFileException, IOException;

    Product getById(Long id) throws FormatFileException, IOException;

    void deleteProductByStoreId(Long id) throws FormatFileException, IOException;

    List<Product> findByCategories(List<Integer> categories);
}
