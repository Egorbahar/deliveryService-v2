package com.exposit.core.service;

import com.exposit.persistence.entity.Category;

import java.util.List;


public interface CategoryService {
    void save(Category category);

    List<Category> getAll();

    Category getById(Long id);

    void delete(Long id);

    void update(Category category);
}
