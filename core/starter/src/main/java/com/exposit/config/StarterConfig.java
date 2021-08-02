package com.exposit.config;

import com.exposit.dao.CategoryDao;
import com.exposit.dao.ProductDao;
import com.exposit.dao.impl.CategoryDaoImpl;
import com.exposit.dao.impl.ProductDaoImpl;
import com.exposit.mapper.CategoryMapper;
import com.exposit.service.CategoryService;
import com.exposit.service.ProductService;
import com.exposit.service.impl.CategoryServiceImpl;
import com.exposit.service.impl.ProductServiceImpl;
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

    @Bean
    public CategoryMapper categoryMapper() {
        return CategoryMapper.INSTANCE;
    }
}
