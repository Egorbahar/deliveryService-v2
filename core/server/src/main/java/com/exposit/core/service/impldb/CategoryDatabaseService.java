package com.exposit.core.service.impldb;

import com.exposit.core.component.LocalMessageSource;
import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Slf4j
public class CategoryDatabaseService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final LocalMessageSource messageSource;

    @Override
    public void save(Category category) {
        log.info("Executing the category save method in the service layer...");
        validate(category.getId() != null, messageSource.getMessage("error.category.notHaveId", new Object[]{}));
        validate(categoryRepository.existsByName(category.getName()), messageSource.getMessage("error.category.name.notUnique", new Object[]{}));
        log.info("Calling the category save method in the repository layer");
        categoryRepository.save(category);
        log.info("The method was executed");
    }

    @Override
    public List<Category> getAll() {
        log.info("Executing the category getAll method in the service layer...");
        log.info("Calling the category findAll method in the repository layer");
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
        log.info("Executing the category update method in the service layer...");
        log.info("Calling the category findById method from the repository layer");
        findById(category.getId());
        validate(categoryRepository.existsByName(category.getName()), messageSource.getMessage("error.category.name.notUnique", new Object[]{}));
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> findCategoriesByParentId(Long id)
    {   log.info("Executing the category save method in the service layer...");
        log.info("Calling the category findCategoriesByParentId method from the repository layer");
        return categoryRepository.findCategoriesByParentId(id);
    }
    @Override
    public Integer findCountProductByCategoryName(String categoryName) {
        log.info("Executing the category findCountProductByCategoryName method in the service layer...");
        log.info("Calling the category countProductsByCategoryName method from the repository layer");
        return categoryRepository.countProductsByCategoryName(categoryName);
    }
}
