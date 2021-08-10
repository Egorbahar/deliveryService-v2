package com.exposit.core.service.impl;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
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
