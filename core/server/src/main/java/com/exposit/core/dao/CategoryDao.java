package com.exposit.core.dao;

import com.exposit.persistence.entity.Category;

import java.util.List;

public interface CategoryDao {
    void save(Category category);

    Category getById(Long id);

    List<Category> getAll();

    void delete(Long id);

    Category update(Category category);

    List<Category> findCategoryByParentId(Long id);

    Integer findCountProductByCategoryName(String categoryName);
}
