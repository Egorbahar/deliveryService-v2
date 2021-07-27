package com.exposit.service.impl;

import com.exposit.dao.CategoryDao;
import com.exposit.exception.FormatFileException;
import com.exposit.model.Category;
import com.exposit.service.CategoryService;

import java.io.IOException;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) throws FormatFileException, IOException {
        categoryDao.save(category);
    }
}
