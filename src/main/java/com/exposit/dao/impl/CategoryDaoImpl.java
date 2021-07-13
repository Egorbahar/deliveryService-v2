package com.exposit.dao.impl;

import com.exposit.dao.CategoryDao;
import com.exposit.model.Category;
import com.exposit.util.PropertyReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(Category category) {
        List<Category> categories = null;
        categories = getAll();
        categories.add(category);
        writeFile(categories);
    }

    public List<Category> getAll() {
        BufferedReader bufferedReader = null;
        try {
            String absolutePath = new File("").getAbsolutePath();
            bufferedReader = new BufferedReader(new FileReader(absolutePath + new PropertyReader().getPropertyValue("CATEGORY_FILE")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        List<Category> categories = gson.fromJson(bufferedReader, type);
        return categories;
    }

    public void writeFile(List<Category> list) {
        String absolutePath = new File("").getAbsolutePath();
        try (FileWriter writer = new FileWriter(absolutePath + new PropertyReader().getPropertyValue("CATEGORY_FILE"))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
