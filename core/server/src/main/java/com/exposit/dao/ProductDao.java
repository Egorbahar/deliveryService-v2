package com.exposit.dao;

import com.exposit.exception.FormatFileException;
import com.exposit.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductDao {
    Product save(Product product) throws FormatFileException, IOException;

    Product getById(Long id) throws FormatFileException, IOException;

    List<Product> getAll() throws FormatFileException, IOException;

    Product delete(Product product) throws FormatFileException, IOException;

    void deleteAllByStoreId(Long id) throws FormatFileException, IOException;

    Product update(Product productUp) throws FormatFileException, IOException;

    List<Product> getAllByStoreId(Long storeId) throws FormatFileException, IOException;
}
