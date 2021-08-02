package com.exposit.service.impl;

import com.exposit.dao.CategoryDao;
import com.exposit.exception.FormatFileException;
import com.exposit.model.Category;
import com.exposit.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public void save(Category category) throws FormatFileException, IOException {
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Category category) {

    }
}
