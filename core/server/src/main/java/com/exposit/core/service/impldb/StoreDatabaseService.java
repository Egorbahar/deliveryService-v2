package com.exposit.core.service.impldb;

import com.exposit.core.annotation.ValidateEntity;
import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.service.StoreService;
import com.exposit.persistence.entity.Store;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.exposit.persistence.specification.StoreSpecification.storeByAddress;
import static com.exposit.persistence.specification.StoreSpecification.storeByName;

@Transactional
@AllArgsConstructor
@Slf4j
public class StoreDatabaseService implements StoreService {
    private final StoreRepository storeRepository;
    private final LocalMessageSource messageSource;

    @Override
    @ValidateEntity
    public void save( Store store){
        log.info("Executing save method in the service layer...");
        storeRepository.save(store);
    }

    @Override
    public void delete(Long id){
        log.info("Executing delete method in the service layer...");
        log.info("Calling deleteById method from the repository layer");
        storeRepository.deleteById(id);
    }

    @Override
    public Store update(Store store){
        log.info("Executing update method in the service layer...");
        log.info("Calling findById method from the repository layer");
        findById(store.getId());
        return storeRepository.saveAndFlush(store);
    }

    @Override
    public List<Store> findAll(){
        log.info("Executing findAll method in the service layer...");
        log.info("Calling findAll method from the repository layer");
        return storeRepository.findAll();
    }

    @Override
    //@ValidateStoreId
    public Store findById(Long id){
        log.info("Executing findById method in the service layer...");
        log.info("Calling findById method from the repository layer");
        return storeRepository.findById(id).orElseThrow(()->new RuntimeException(messageSource.getMessage("error.store.notExist", new Object[]{})));
    }
    @Override
    public List<Store> findByProductNameWithMinProductPrice(String productName)
    {
        log.info("Executing findByProductNameWithMinProductPrice method in the service layer...");
        log.info("Calling findStoreByMinPriceOfProductName method from the repository layer");
        return storeRepository.findStoreByMinPriceOfProductName(productName);
    }
    @Override
    public List<Store>  findAllStoresWhereProductIsInStock(String productName)
    {
        log.info("Executing findAllStoresWhereProductIsInStock method in the service layer...");
        log.info("Calling  findAllStoresWhereProductIsInStock method from the repository layer");
        return storeRepository.findAllByProductNameAndProductQuantityGreaterThan(productName,0);
    }

    @Override
    public List<Store> filterByNameOrAddress(String name, String address) {
        log.info("Executing filterByNameOrAddress method in the service layer...");
        log.info("Calling findAll(with specification) method from the repository layer");
        return storeRepository.findAll(Specification.where((storeByName(name))).or(storeByAddress(address)));
    }
}
