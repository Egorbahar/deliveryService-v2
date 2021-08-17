package com.exposit.core.service.implDB;

import com.exposit.core.exception.FormatFileException;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
@AllArgsConstructor
public class StoreDataBaseService implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public void add(Store store) throws IOException, FormatFileException {

    }

    @Override
    public void deleteStore(Long id) throws FormatFileException, IOException {

    }

    @Override
    public void updateStore(Store store) throws FormatFileException, IOException {

    }

    @Override
    public List<Store> getAll() throws FormatFileException, IOException {
        return null;
    }

    @Override
    public Store getById(Long id) throws FormatFileException, IOException {
        return null;
    }
}
