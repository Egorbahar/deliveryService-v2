package com.exposit.service;

import com.exposit.exception.FormatFileException;
import com.exposit.model.Category;
import com.exposit.model.Product;

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
