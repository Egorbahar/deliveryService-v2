package com.ex.service;

import com.ex.exception.FormatFileException;
import com.ex.model.Store;

import java.io.IOException;
import java.util.List;


public interface StoreService {
    void add(Store store) throws IOException, FormatFileException;

    void deleteStore(Long id) throws FormatFileException, IOException;

    void updateStore(Store store) throws FormatFileException, IOException;

    List<Store> getAll() throws FormatFileException, IOException;

    Store getById(Long id) throws FormatFileException, IOException;
}
