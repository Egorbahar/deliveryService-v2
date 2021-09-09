package com.exposit.persistence.repository;

import com.exposit.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    @Query("SELECT product FROM Product product  JOIN product.categories category " +
            "WHERE category.id = :categoryId " +
            "AND product.price < (SELECT AVG(product.price) FROM product)")
    List<Product> findProductsByCategoryAndAvrPrice(@Param("categoryId") Long categoryId);

    List<Product> findProductsByQuantityGreaterThan(int quantity);

    List<Product> findProductsByQuantity(int quantity);

    @Query("SELECT product FROM Product product  JOIN product.categories category WHERE category.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") Long categoryId);

}
