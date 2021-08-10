package com.exposit.core.service;

import com.exposit.core.exception.FormatFileException;
import com.exposit.persistence.entity.Category;

import java.io.IOException;
import java.util.List;


public interface CategoryService {
    void save(Category category) throws FormatFileException, IOException;

    List<Category> getAll();

    Category getById(Long id);

    void delete(Long id);

    void update(Category category);
}
