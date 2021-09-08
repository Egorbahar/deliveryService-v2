package com.exposit.core.service.impldb;

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
        Category category = new Category();
        if (id != null) {
            category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.category.notExist", new Object[]{})));
        }
        return category;
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

    @Override
    public List<Category> findCategoriesByParentId(Long id) {
        return categoryRepository.findCategoriesByParentId(id);
    }
    @Override
    public Integer findCountProductByCategoryName(String categoryName) {
        return categoryRepository.countProductsByCategoryName(categoryName);
    }
}
