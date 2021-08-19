package com.exposit.core.service.implDB;

import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
public class StoreDatabaseService implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public void add(Store store){
        storeRepository.save(store);
    }

    @Override
    public void delete(Long id){
        storeRepository.deleteById(id);
    }

    @Override
    public Store update(Store store){
        findById(store.getId());
        return storeRepository.saveAndFlush(store);
    }

    @Override
    public List<Store> getAll(){
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id){
        return storeRepository.findById(id).orElseThrow(()->new RuntimeException(""));
    }
}
