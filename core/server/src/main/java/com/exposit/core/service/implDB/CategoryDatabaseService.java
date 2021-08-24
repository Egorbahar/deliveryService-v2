package com.exposit.core.service.implDB;

import com.exposit.core.component.LocalMessageSource;
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
    private final LocalMessageSource messageSource;

    @Override
    public void save(Category category) {
        validate(category.getId() != null, messageSource.getMessage("error.category.notHaveId", new Object[]{}));
        validate(categoryRepository.existsByName(category.getName()), messageSource.getMessage("error.category.name.notUnique", new Object[]{}));
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.category.notExist", new Object[]{})));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        findById(category.getId());
        validate(categoryRepository.existsByName(category.getName()), messageSource.getMessage("error.category.name.notUnique", new Object[]{}));
        return categoryRepository.saveAndFlush(category);
    }
}
