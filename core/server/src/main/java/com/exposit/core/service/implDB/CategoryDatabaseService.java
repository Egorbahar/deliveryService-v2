package com.exposit.core.service.implDB;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
public class CategoryDatabaseService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        findById(category.getId());
        return categoryRepository.saveAndFlush(category);
    }
}
