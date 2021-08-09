package com.exposit.core.dao;

import com.exposit.core.model.Store;
import com.exposit.web.exception.FormatFileException;

import java.io.IOException;
import java.util.List;

public interface StoreDao {
    void save(Store store) throws IOException, FormatFileException;

    Store getById(Long id) throws FormatFileException, IOException;

    List<Store> getAll() throws FormatFileException, IOException;

    void delete(Long id) throws FormatFileException, IOException;

    Store update(Store storeUp) throws FormatFileException, IOException;
}
