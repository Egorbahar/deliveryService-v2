package com.exposit.core;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.dao.ProductDao;
import com.exposit.core.dao.impl.CategoryDaoImpl;
import com.exposit.core.dao.impl.ProductDaoImpl;
import com.exposit.core.service.CategoryService;
import com.exposit.core.service.ProductService;
import com.exposit.core.service.impl.CategoryServiceImpl;
import com.exposit.core.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class StarterConfig {
    @Bean
    public ProductService productService(ProductDao productDao) {
        return new ProductServiceImpl(productDao);
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDaoImpl();
    }

    @Bean
    public CategoryService categoryService(CategoryDao categoryDao) {
        return new CategoryServiceImpl(categoryDao);
    }

    @Bean
    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }

}
