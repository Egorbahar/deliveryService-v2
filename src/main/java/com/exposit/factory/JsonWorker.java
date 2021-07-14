package com.exposit.factory;

import com.exposit.util.PropertyReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonWorker<T> implements Worker<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void write(String propertyName, List<T> list) {
        String absolutePath = new File("").getAbsolutePath();
        try (FileWriter writer = new FileWriter(absolutePath + new PropertyReader().getPropertyValue(propertyName))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> read(String propertyName) {
        List<T> list;
        BufferedReader bufferedReader = null;
        try {
            String absolutePath = new File("").getAbsolutePath();
            bufferedReader = new BufferedReader(new FileReader(absolutePath + new PropertyReader().getPropertyValue(propertyName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<T>>() {
        }.getType();
        list = gson.fromJson(bufferedReader, type);
        return list;
    }
}
