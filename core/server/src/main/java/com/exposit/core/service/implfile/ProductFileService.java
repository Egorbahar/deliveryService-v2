package com.exposit.core.service.implfile;

import com.exposit.core.dao.ProductDao;
import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<Product> findProductsByCategoryId(Long categoryId) {
       Category category = productDao.getAll().stream()
                                     .flatMap(master -> master.getCategories().stream()
                                           .filter(order -> order.getId().equals(categoryId)))
                                     .findFirst()
                                     .get();
       return category.getProducts();
    }


    @Override
    public List<Product> getAll() {
        return productDao.getAll();
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
        return productDao.getAll().stream()
                .filter(p->p.getPrice()>0)
                .collect(Collectors.toList());
    }

}
