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
public class ProductDataBaseService implements ProductService {
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
    public Product delete(Long id) {
        return null;
    }

    @Override
    public void updateProduct(Product productUp) {

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
        return null;
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public void deleteProductByStoreId(Long id) {

    }

    @Override
    public List<Product> findByCategories(List<Integer> categories) {
        return null;
    }
}
