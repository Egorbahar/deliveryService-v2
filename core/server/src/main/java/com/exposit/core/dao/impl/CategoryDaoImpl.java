package com.exposit.core.dao.impl;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.factorymethod.ParserFactory;
import com.exposit.persistence.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void save(Category category) {
        if (category.getId() == null) {
            category.setId((long) (Math.random() * 100));
        }
        List<Category> categories = getAll();
        categories.add(category);
        writeFile(categories);
    }

    @Override
    public Category getById(Long id) {
        return getAll().stream()
                       .filter(c -> c.getId().equals(id))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("The category was not found with the category id:" + id));
    }


    public List<Category> getAll() {
        ParserFactory parserFactory = new ParserFactory();
        try {
            return parserFactory.getParser().read("category");
        } catch (IOException | FormatFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        List<Category> categories = getAll();
        Category deleteCategory = categories.stream()
                                            .filter(c -> c.getId().equals(id))
                                            .findFirst()
                                            .get();
        categories.remove(deleteCategory);
        writeFile(categories);
    }

    @Override
    public Category update(Category categoryUp) {
        Category category = getById(categoryUp.getId());
        List<Category> updateList = getAll();
        updateList.set(updateList.indexOf(category), categoryUp);
        writeFile(updateList);
        return category;
    }

    public void writeFile(List<Category> list) {
        ParserFactory parserFactory = new ParserFactory();
        try {
            parserFactory.getParser().write("category", list);
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> findCategoryByParentId(Long id) {
        return getAll().stream()
                       .filter(c -> c.getParent().getId().equals(id))
                       .collect(Collectors.toList());
    }

    @Override
    public Integer findCountProductByCategoryName(String categoryName) {
        Category category = getAll().stream()
                                              .filter(c -> c.getName().equals(categoryName))
                                              .findFirst()
                                              .get();
       return category.getProducts().size();
    }
}
