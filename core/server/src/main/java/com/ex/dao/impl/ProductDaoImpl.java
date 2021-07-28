package com.ex.dao.impl;

import com.ex.dao.ProductDao;
import com.ex.exception.FormatFileException;
import com.ex.factorymethod.ParserFactory;
import com.ex.model.Product;
import com.ex.model.Store;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoImpl implements ProductDao {

    public Product save(Product product) throws FormatFileException, IOException {
        if (product.getId() == null) {
            product.setId((long) (Math.random() * 100));
        }
        List<Product> products = getAll();
        products.add(product);
        writeFile(products);
        return product;
    }

    public Product getById(Long id) throws FormatFileException, IOException {
        return getAll().stream()
                       .filter(p -> p.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("The product was not found with the product id:" + id));
    }

    public List<Product> getAll() throws FormatFileException, IOException {
        ParserFactory parserFactory = new ParserFactory();
        return parserFactory.getParser().read("product");
    }

    public Product delete(Product product) throws FormatFileException, IOException {
        List<Product> products = getAll();
        Product deleteProd = products.stream()
                                     .filter(p -> p.getId().equals(product.getId()))
                                     .findFirst()
                                     .get();
        products.remove(deleteProd);
        writeFile(products);
        return deleteProd;
    }

    @Override
    public void deleteAllByStoreId(Long id) throws FormatFileException, IOException {
        List<Product> products = getAll();
        if (!products.isEmpty() && products != null) {
            List<Store> stores = products.stream()
                                         .flatMap(p -> p.getStores().stream())
                                         .collect(Collectors.toList());
            Store findStore = stores.stream()
                                .filter(s -> s.getId().equals(id))
                                .findFirst()
                                .get();
            List<Product> filteredProducts = products.stream()
                                                     .filter(p -> !p.getStores().contains(findStore))
                                                     .collect(Collectors.toList());
            writeFile(filteredProducts);
        }
    }

    public Product update(Product productUp) throws FormatFileException, IOException {
        Product product = getById(productUp.getId());
        List<Product> updateList = getAll();
        updateList.set(updateList.indexOf(product), productUp);
        writeFile(updateList);
        return product;
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) throws FormatFileException, IOException {
        List<Store> stores = getAll().stream()
                                     .flatMap(p -> p.getStores().stream())
                                     .collect(Collectors.toList());
        Store findStore = stores.stream()
                                .filter(s -> s.getId().equals(storeId))
                                .findFirst()
                                .get();
        return getAll().stream()
                       .filter(p -> p.getStores().contains(findStore))
                       .collect(Collectors.toList());
    }

    public void writeFile(List<Product> list) throws FormatFileException, IOException {
        ParserFactory parserFactory = new ParserFactory();
        parserFactory.getParser().write("product",list);
    }
}
