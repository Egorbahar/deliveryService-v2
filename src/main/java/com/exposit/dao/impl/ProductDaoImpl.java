package com.exposit.dao.impl;

import com.exposit.dao.ProductDao;
import com.exposit.factorymethod.ParserFactory;
import com.exposit.model.Product;
import com.exposit.model.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoImpl implements ProductDao {

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId((long) (Math.random() * 100));
        }
        List<Product> products = getAll();
        products.add(product);
        writeFile(products);
        return product;
    }

    public Product getById(Long id) {
        return getAll().stream()
                       .filter(p -> p.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("The product was not found with the product id:" + id));
    }

    public List<Product> getAll() {
        ParserFactory parserFactory = new ParserFactory();
        return parserFactory.getParser().read("product");
    }

    public Product delete(Product product) {
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
    public void deleteAllByStoreId(Long id) {
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

    public Product update(Product productUp) {
        Product product = getById(productUp.getId());
        List<Product> updateList = getAll();
        updateList.set(updateList.indexOf(product), productUp);
        writeFile(updateList);
        return product;
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) {
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

    public void writeFile(List<Product> list) {
        ParserFactory parserFactory = new ParserFactory();
        parserFactory.getParser().write("product",list);
    }
}
