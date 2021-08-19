package com.exposit.core;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.dao.ProductDao;
import com.exposit.core.dao.StoreDao;
import com.exposit.core.dao.impl.CategoryDaoImpl;
import com.exposit.core.dao.impl.ProductDaoImpl;
import com.exposit.core.dao.impl.StoreDaoImpl;
import com.exposit.core.service.CategoryService;
import com.exposit.core.service.ProductService;
import com.exposit.core.service.StoreService;
import com.exposit.core.service.impl.CategoryServiceImpl;
import com.exposit.core.service.impl.ProductServiceImpl;
import com.exposit.core.service.impl.StoreServiceImpl;
import com.exposit.core.service.implDB.CategoryDatabaseService;
import com.exposit.core.service.implDB.ProductDatabaseService;
import com.exposit.core.service.implDB.StoreDatabaseService;
import com.exposit.persistence.repository.CategoryRepository;
import com.exposit.persistence.repository.ProductRepository;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class StarterConfig {
    private final Environment environment;


    @Bean
    public ProductService productService(ProductDao productDao, ProductRepository productRepository) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new ProductDatabaseService(productRepository);
        } else {
            return new ProductServiceImpl(productDao);
        }
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDaoImpl();
    }

    @Bean
    public CategoryService categoryService(CategoryDao categoryDao, CategoryRepository categoryRepository) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new CategoryDatabaseService(categoryRepository);
        } else {
            return new CategoryServiceImpl(categoryDao);
        }
    }

    @Bean
    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }
    @Bean
    public StoreService storeService(StoreDao storeDao, StoreRepository storeRepository) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new StoreDatabaseService(storeRepository);
        } else {
            return new StoreServiceImpl(storeDao);
        }
    }
    @Bean
    public StoreDao storeDao() {
        return new StoreDaoImpl();
    }
}
