package com.ex.service.impl;

import com.ex.dao.CategoryDao;
import com.ex.exception.FormatFileException;
import com.ex.model.Category;
import com.ex.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public void save(Category category) throws FormatFileException, IOException {
        categoryDao.save(category);
    }
}
