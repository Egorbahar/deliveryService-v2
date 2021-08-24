package com.exposit.core.dao.impl;

import com.exposit.core.dao.ProductDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.factorymethod.ParserFactory;
import com.exposit.persistence.entity.Product;

import java.io.IOException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public Product save(Product product)  {
        if (product.getId() == null) {
            product.setId((long) (Math.random() * 100));
        }
        List<Product> products = getAll();
        products.add(product);
        writeFile(products);
        return product;
    }

    public Product getById(Long id)  {
        return getAll().stream()
                       .filter(p -> p.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("The product was not found with the product id:" + id));
    }

    public List<Product> getAll()  {
        ParserFactory parserFactory = new ParserFactory();
        try {
            return parserFactory.getParser().read("product");
        } catch (IOException | FormatFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Long id){
        List<Product> products = getAll();
        Product deleteProd = products.stream()
                                     .filter(p -> p.getId().equals(id))
                                     .findFirst()
                                     .get();
        products.remove(deleteProd);
        writeFile(products);
    }

    @Override
    public void deleteAllByStoreId(Long id) {
//        List<Product> products = getAll();
//        if (!products.isEmpty() && products != null) {
//            List<Store> stores = products.stream()
//                                         .flatMap(p -> p.getStores().stream())
//                                         .collect(Collectors.toList());
//            Store findStore = stores.stream()
//                                .filter(s -> s.getId().equals(id))
//                                .findFirst()
//                                .get();
//            List<Product> filteredProducts = products.stream()
//                                                     .filter(p -> !p.getStores().contains(findStore))
//                                                     .collect(Collectors.toList());
//            writeFile(filteredProducts);
//        }
    }

    public Product update(Product productUp){
        Product product = getById(productUp.getId());
        List<Product> updateList = getAll();
        updateList.set(updateList.indexOf(product), productUp);
        writeFile(updateList);
        return product;
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) {
//        List<Store> stores = getAll().stream()
//                                     .flatMap(p -> p.getStores().stream())
//                                     .collect(Collectors.toList());
//        Store findStore = stores.stream()
//                                .filter(s -> s.getId().equals(storeId))
//                                .findFirst()
//                                .get();
//        return getAll().stream()
//                       .filter(p -> p.getStores().contains(findStore))
//                       .collect(Collectors.toList());
        return null;
    }

    public void writeFile(List<Product> list) {
        ParserFactory parserFactory = new ParserFactory();
        try {
            parserFactory.getParser().write("product",list);
        } catch (FormatFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
