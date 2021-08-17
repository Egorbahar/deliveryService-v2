package com.exposit.core.service.implDB;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
public class CategoryDataBaseService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
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
