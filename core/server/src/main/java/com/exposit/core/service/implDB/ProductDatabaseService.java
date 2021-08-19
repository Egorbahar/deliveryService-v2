package com.exposit.core.service.implDB;

import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;
import com.exposit.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@AllArgsConstructor
public class ProductDatabaseService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> sortByPrice(Long storeId) {
        return null;
    }

    @Override
    public void delete(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product productUp) {
        findById(productUp.getId());
        return productRepository.saveAndFlush(productUp);
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
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException(""));
    }

    @Override
    public void deleteProductByStoreId(Long id) {

    }

    @Override
    public List<Product> findByCategories(List<Integer> categories) {
        return null;
    }
}
