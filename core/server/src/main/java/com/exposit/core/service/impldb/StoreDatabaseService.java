package com.exposit.core.service.impldb;

import com.exposit.core.annotation.ValidateEntity;
import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.exposit.persistence.specification.StoreSpecification.storeByAddress;
import static com.exposit.persistence.specification.StoreSpecification.storeByName;

@Transactional
@AllArgsConstructor
public class StoreDatabaseService implements StoreService {
    private final StoreRepository storeRepository;
    private final LocalMessageSource messageSource;

    @Override
    @ValidateEntity
    public void save( Store store){
//        validate(store.getId() != null, messageSource.getMessage("error.store.notHaveId", new Object[]{}));
//        validate(storeRepository.existsByAddress(store.getAddress()), messageSource.getMessage("error.store.address.notUnique", new Object[]{}));
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

    @Override
    public List<Store> filterByNameOrAddress(String name, String address) {
        return storeRepository.findAll(Specification.where((storeByName(name))).or(storeByAddress(address)));
    }
}
