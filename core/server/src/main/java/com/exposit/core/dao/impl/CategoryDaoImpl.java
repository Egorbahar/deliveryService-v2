package com.exposit.core.dao.impl;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.factorymethod.ParserFactory;
import com.exposit.persistence.entity.Category;

import java.io.IOException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void save(Category category){
        if (category.getId() == null) {
            category.setId((long) (Math.random() * 100));
        }
        List<Category> categories = getAll();
        categories.add(category);
        writeFile(categories);
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    public List<Category> getAll(){
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

    }

    @Override
    public Category update(Category category) {
        return null;
    }

    public void writeFile(List<Category> list){
        ParserFactory parserFactory = new ParserFactory();
        try {
            parserFactory.getParser().write("category",list);
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
