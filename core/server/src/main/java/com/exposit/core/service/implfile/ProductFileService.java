package com.exposit.core.service.implfile;

import com.exposit.core.dao.ProductDao;
import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
public class ProductFileService implements ProductService {
    private final ProductDao productDao;

    public Product save(Product product) {
        return productDao.save(product);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public Product update(Product product) {
        productDao.update(product);
        return product;
    }

    @Override
    public List<Product> findByAttributes(Map<Integer, String> attributes) {
        return productDao.getAll()
                         .stream()
                         .filter(p -> filterIfContainsAttribute(attributes, 1, Product::getName).test(p))
                         .filter(p -> filterIfContainsAttribute(attributes, 2, Product::valueOfPrice).test(p))
                         .collect(Collectors.toList());
    }

    private Predicate<Product> filterIfContainsAttribute(Map<Integer, String> attributes, Integer attributeIndex, Function<Product, String> f) {
        return object -> !attributes.containsKey(attributeIndex) || f.apply(object).equals(attributes.get(attributeIndex));
    }

    @Override
    public List<Product> findByCategory(Category category) {
//        return productDao.getAll().stream()
//                         .filter(product -> product.getCategories().contains(category))
//                         .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> getAllByStoreId(Long storeId) {
        return productDao.getAllByStoreId(storeId);
    }

    @Override
    public Product findById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> findProductByCategoryIdWithPriceLessAvg(Long categoryId) {
        return null;
    }

    @Override
    public List<Product> findProductInStock(boolean param) {
        return null;
    }

    public List<Product> sortByPrice(Long storeId) {
//        return productDao.getAll().stream()
//                         .filter(product -> product.getStore().getId().equals(storeId))
//                         .sorted(Comparator.comparing(Product::getPrice))
//                         .collect(Collectors.toList());
        return null;
    }
}
