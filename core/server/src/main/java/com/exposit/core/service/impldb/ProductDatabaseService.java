package com.exposit.core.service.impldb;

import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Product;
import com.exposit.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Slf4j
public class ProductDatabaseService implements ProductService {
    private final ProductRepository productRepository;
    private final LocalMessageSource messageSource;

    @Override
    public Product save(Product product) {
        log.info("Executing save method in the service layer...");
        validate(product.getId() != null, messageSource.getMessage("error.product.notHaveId", new Object[]{}));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        log.info("Executing delete method in the service layer...");
        log.info("Calling deleteById method from the repository layer");
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        log.info("Executing delete method in the service layer...");
        log.info("Calling findById method from the repository layer");
        findById(product.getId());
        validate(productRepository.existsByName(product.getName()), messageSource.getMessage("error.product.name.notUnique", new Object[]{}));
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAll() {
        log.info("Executing getAll method in the service layer...");
        log.info("Calling findAll method from the repository layer");
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        log.info("Executing getAll method in the service layer...");
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.product.notExist", new Object[]{})));
    }


    public List<Product> findProductByCategoryIdWithPriceLessAvg(Long categoryId) {
        log.info("Executing findProductByCategoryIdWithPriceLessAvg method in the service layer...");
        log.info("Calling findProductsByCategoryAndAvrPrice method from the repository layer");
        return productRepository.findProductsByCategoryAndAvrPrice(categoryId);
    }

    @Override
    public List<Product> findProductInStock(boolean isInStock) {
        log.info("Executing findProductInStock method in the service layer...");
        return isInStock ? productRepository.findProductsByQuantityGreaterThan(0) : productRepository.findProductsByQuantity(0);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        log.info("Executing findProductsByCategoryId method in the service layer...");
        log.info("Calling findProductsByCategoryId method from the repository layer");
        return productRepository.findProductsByCategoryId(categoryId);
    }
}
