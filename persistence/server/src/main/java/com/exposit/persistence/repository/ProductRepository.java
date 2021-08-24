package com.exposit.persistence.repository;

import com.exposit.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByName(String name);

    List<Product> findByNameIsStartingWith(String str);

    boolean existsByName(String name);
}
