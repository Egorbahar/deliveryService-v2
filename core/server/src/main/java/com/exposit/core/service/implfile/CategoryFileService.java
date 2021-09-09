package com.exposit.core.service.implfile;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@AllArgsConstructor
public class CategoryFileService implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.getById(id);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public List<Category> findCategoriesByParentId(Long id) {
        return categoryDao.findCategoryByParentId(id);
    }

    @Override
    public Integer findCountProductByCategoryName(String categoryName) {
        return categoryDao.findCountProductByCategoryName(categoryName);
    }
}
