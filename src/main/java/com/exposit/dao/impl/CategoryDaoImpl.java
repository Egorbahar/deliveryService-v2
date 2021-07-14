package com.exposit.dao.impl;

import com.exposit.dao.CategoryDao;
import com.exposit.model.Category;
import com.exposit.factory.JsonParserFactory;
import com.exposit.factory.ParserFactory;
import com.exposit.factory.Worker;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void save(Category category) {
        List<Category> categories = null;
        categories = getAll();
        categories.add(category);
        writeFile(categories);
    }

    public List<Category> getAll() {
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        return worker.read("CATEGORY_FILE");
    }

    public void writeFile(List<Category> list) {
        ParserFactory parserFactory = new JsonParserFactory();
        Worker worker = parserFactory.createWorker();
        worker.write("CATEGORY_FILE", list);
    }
}
