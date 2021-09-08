package com.exposit.persistence.repository;

import com.exposit.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);

    @Query("SELECT category FROM Category category WHERE category.parent.id = :id")
    List<Category> findCategoriesByParentId(@Param("id") Long id);

    @Query("SELECT COUNT(product) FROM Product product  JOIN product.categories category WHERE category.name = :categoryName")
    Integer countProductsByCategoryName(@Param("categoryName") String categoryName);
}
