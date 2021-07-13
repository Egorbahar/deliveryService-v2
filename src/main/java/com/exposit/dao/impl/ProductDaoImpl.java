package com.exposit.dao.impl;

import com.exposit.dao.ProductDao;
import com.exposit.model.Product;
import com.exposit.model.Store;
import com.exposit.util.PropertyReader;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoImpl implements ProductDao {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().setExclusionStrategies(new ExclusionForProduct()).create();

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
        List<Product> products = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            String absolutePath = new File("").getAbsolutePath();
            bufferedReader = new BufferedReader(new FileReader(absolutePath + new PropertyReader().getPropertyValue("PRODUCT_FILE")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        products = gson.fromJson(bufferedReader, type);
        return products;
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
        String absolutePath = new File("").getAbsolutePath();
        try (FileWriter writer = new FileWriter(absolutePath + new PropertyReader().getPropertyValue("PRODUCT_FILE"))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ExclusionForProduct implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return (fieldAttributes.getDeclaringClass() == Store.class && fieldAttributes.getName().equals("address"))
                    || (fieldAttributes.getDeclaringClass() == Store.class && fieldAttributes.getName().equals("products"));
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return false;
        }
    }


}
