package com.exposit.core.dao.impl;

import com.exposit.core.dao.CategoryDao;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.factorymethod.ParserFactory;
import com.exposit.core.model.Category;

import java.io.IOException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void save(Category category) throws FormatFileException, IOException {
        if (category.getId() == null) {
            category.setId((long) (Math.random() * 100));
        }
        List<Category> categories = getAll();
        categories.add(category);
        writeFile(categories);
    }

    public List<Category> getAll() throws FormatFileException, IOException {
        ParserFactory parserFactory = new ParserFactory();
        return parserFactory.getParser().read("category");
    }

    public void writeFile(List<Category> list) throws FormatFileException, IOException {
        ParserFactory parserFactory = new ParserFactory();
        parserFactory.getParser().write("category",list);
    }
}
