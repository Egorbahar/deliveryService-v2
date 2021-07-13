package com.exposit.service.impl;

import com.exposit.dao.CategoryDao;
import com.exposit.model.Category;
import com.exposit.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }
}
