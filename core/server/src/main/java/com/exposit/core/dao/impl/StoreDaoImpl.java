package com.exposit.core.dao.impl;

import com.exposit.core.dao.StoreDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.factorymethod.ParserFactory;
import com.exposit.persistence.entity.Store;

import java.io.IOException;
import java.util.List;

public class StoreDaoImpl implements StoreDao {

    public void save(Store store) {
        if (store.getId() == null) {
            store.setId((long) (Math.random() * 100));
        }
        List<Store> stores = getAll();
        stores.add(store);
        writeFile(stores);
    }

    public Store getById(Long id) {
        return getAll().stream()
                       .filter(s -> s.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("The store was not found with the store id:" + id));
    }

    public List<Store> getAll() {
        ParserFactory parserFactory = new ParserFactory();
        try {
            return parserFactory.getParser().read("store");
        } catch (IOException | FormatFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Long id) {
        List<Store> stores = getAll();
        Store deleteStore = stores.stream()
                                  .filter(s -> s.getId().equals(id))
                                  .findFirst()
                                  .get();
        stores.remove(deleteStore);
        writeFile(stores);
    }

    public Store update(Store storeUp) {
        Store store = getById(storeUp.getId());
        List<Store> updateList = getAll();
        updateList.set(updateList.indexOf(store), storeUp);
        writeFile(updateList);
        return store;
    }

    public void writeFile(List<Store> list) {
        ParserFactory parserFactory = new ParserFactory();
        try {
            parserFactory.getParser().write("store", list);
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }


    }

}
