package com.exposit.core.service;

import com.exposit.persistence.entity.Category;

import java.util.List;


public interface CategoryService extends DefaultService {
    void save(Category category);

    List<Category> getAll();

    Category findById(Long id);

    void delete(Long id);

    Category update(Category category);

    List<Category> findCategoriesByParentId(Long id);

    Integer findCountProductByCategoryName(String categoryName);
}
