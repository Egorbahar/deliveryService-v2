package com.exposit.core;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.dao.ProductDao;
import com.exposit.core.dao.impl.CategoryDaoImpl;
import com.exposit.core.dao.impl.ProductDaoImpl;
import com.exposit.core.service.CategoryService;
import com.exposit.core.service.ProductService;
import com.exposit.core.service.impl.CategoryServiceImpl;
import com.exposit.core.service.impl.ProductServiceImpl;
import com.exposit.core.service.implDB.CategoryDataBaseService;
import com.exposit.core.service.implDB.ProductDataBaseService;
import com.exposit.persistence.repository.CategoryRepository;
import com.exposit.persistence.repository.ProductRepository;
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
            return new ProductDataBaseService(productRepository);
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
            return new CategoryDataBaseService(categoryRepository);
        } else {
            return new CategoryServiceImpl(categoryDao);
        }
    }

    @Bean
    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }

}
