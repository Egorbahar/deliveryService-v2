package com.exposit.dao.impl;

import com.exposit.dao.ProductDao;
import com.exposit.model.Product;
import com.exposit.factory.JsonParserFactory;
import com.exposit.factory.ParserFactory;
import com.exposit.factory.Worker;

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
                       .orElse(null);
    }

    public List<Product> getAll() {
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        return worker.read("PRODUCT_FILE");
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
            List<Product> filteredProducts = products.stream()
                                                     .filter(p -> !p.getStore().getId().equals(id))
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
        return getAll().stream()
                       .filter(p -> p.getStore().getId().equals(storeId))
                       .collect(Collectors.toList());
    }

    public void writeFile(List<Product> list) {
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        worker.write("PRODUCT_FILE", list);
    }
}
