package com.exposit.core.service.implDB;

import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;
import com.exposit.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@PropertySource("starter/src/resources/exception.properties")
public class ProductDatabaseService implements ProductService {
    private final ProductRepository productRepository;
    private final LocalMessageSource messageSource;
    @Override
    public Product save(Product product) {
        validate(product.getId() != null, messageSource.getMessage("error.store.notHaveId", new Object[]{}));
        validate(productRepository.existsByName(product.getName()), messageSource.getMessage("error.product.name.notUnique", new Object[]{}));
        return productRepository.save(product);
    }

    @Override
    public List<Product> sortByPrice(Long storeId) {
        return productRepository.findAll().stream()
                                .sorted(Comparator.comparingDouble(Product::getPrice))
                                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        findById(product.getId());
        validate(productRepository.existsByName(product.getName()), messageSource.getMessage("error.product.name.notUnique", new Object[]{}));
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> findByAttributes(Map<Integer, String> attributes) {
        return null;
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.product.notExist", new Object[]{})));
    }

    @Override
    public void deleteProductByStoreId(Long id) {

    }


    @Override
    public List<Product> findByCategories(List<Integer> categories) {
        return null;
    }
}
