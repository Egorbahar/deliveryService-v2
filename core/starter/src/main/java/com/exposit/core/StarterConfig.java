package com.exposit.core;

import com.exposit.core.aspect.ValidationAspect;
import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.dao.CategoryDao;
import com.exposit.core.dao.ProductDao;
import com.exposit.core.dao.StoreDao;
import com.exposit.core.dao.impl.CategoryDaoImpl;
import com.exposit.core.dao.impl.ProductDaoImpl;
import com.exposit.core.dao.impl.StoreDaoImpl;
import com.exposit.core.service.CategoryService;
import com.exposit.core.service.ProductService;
import com.exposit.core.service.StoreService;
import com.exposit.core.service.impldb.CategoryDatabaseService;
import com.exposit.core.service.impldb.ProductDatabaseService;
import com.exposit.core.service.impldb.StoreDatabaseService;
import com.exposit.core.service.implfile.CategoryFileService;
import com.exposit.core.service.implfile.ProductFileService;
import com.exposit.core.service.implfile.StoreFileService;
import com.exposit.persistence.repository.CategoryRepository;
import com.exposit.persistence.repository.ProductRepository;
import com.exposit.persistence.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
@AllArgsConstructor
public class StarterConfig {
    private final Environment environment;


    @Bean
    public ProductService productService(ProductDao productDao, ProductRepository productRepository, LocalMessageSource messageSource) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new ProductDatabaseService(productRepository, messageSource);
        } else {
            return new ProductFileService(productDao);
        }

    }

    @Bean
    public ProductDao productDao() {
        return new ProductDaoImpl();
    }

    @Bean
    public CategoryService categoryService(CategoryDao categoryDao, CategoryRepository categoryRepository, LocalMessageSource messageSource) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new CategoryDatabaseService(categoryRepository, messageSource);
        } else {
            return new CategoryFileService(categoryDao);
        }
    }

    @Bean
    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }

    @Bean
    public StoreService storeService(StoreDao storeDao, StoreRepository storeRepository, LocalMessageSource messageSource) {
        if (environment.getRequiredProperty("datasource.type").equals("JPA")) {
            return new StoreDatabaseService(storeRepository, messageSource);
        } else {
            return new StoreFileService(storeDao);
        }
    }

    @Bean
    public StoreDao storeDao() {
        return new StoreDaoImpl();
    }

    @Bean
    public LocalMessageSource localMessageSource(MessageSource messageSource) {
        return new LocalMessageSource(messageSource);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("applicationMessages");
        return messageSource;
    }

    @Bean
    public ValidationAspect validationAspect(LocalMessageSource messageSource, StoreRepository storeRepository) {
        return new ValidationAspect(messageSource, storeRepository);
    }
}

