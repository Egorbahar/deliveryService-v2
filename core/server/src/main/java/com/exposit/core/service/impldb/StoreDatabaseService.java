package com.exposit.core.service.impldb;

import com.exposit.core.component.LocalMessageSource;
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
    private final LocalMessageSource messageSource;

    @Override
    public void save(Store store){
        validate(store.getId() != null, messageSource.getMessage("error.store.notHaveId", new Object[]{}));
        validate(storeRepository.existsByAddress(store.getAddress()), messageSource.getMessage("error.store.address.notUnique", new Object[]{}));
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
        return storeRepository.findById(id).orElseThrow(()->new RuntimeException(messageSource.getMessage("error.store.notExist", new Object[]{})));
    }
    @Override
    public List<Store> findByProductNameWithMinProductPrice(String productName)
    {
        return storeRepository.findStoreByMinPriceOfProductName(productName);
    }
    @Override
    public List<Store>  findAllStoresWhereProductIsInStock(String productName)
    {
        return storeRepository.findAllByProductNameAndProductQuantityGreaterThan(productName,0);
    }
}
