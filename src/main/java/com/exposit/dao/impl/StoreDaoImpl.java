package com.exposit.dao.impl;

import com.exposit.dao.StoreDao;
import com.exposit.factory.JsonParserFactory;
import com.exposit.model.Store;
import com.exposit.factory.ParserFactory;
import com.exposit.factory.Worker;
import com.exposit.factory.XmlParserFactory;

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
                       .filter(s -> s.getId().equals(id)).findFirst().orElse(null);//написать exp
    }

    public List<Store> getAll() {
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        return worker.read("STORE_FILE");
//        ParserFactory parserFactory = new XmlParserFactory();
//        Worker worker = parserFactory.createWorker();
//        return worker.read("STORE_XML_FILE");
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
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        worker.write("STORE_FILE",list);
//        ParserFactory parserFactory = new XmlParserFactory();
//        Worker worker = parserFactory.createWorker();
//        worker.write("STORE_XML_FILE",list);


    }

}
