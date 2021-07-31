package com.exposit.factorymethod;

import com.exposit.util.PropertyReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser<T> implements Parser<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void write(String className, List<T> list) {
        String absolutePath = new File("app/").getAbsolutePath();
        String propertyValue = className + ".file.json";
        try (FileWriter writer = new FileWriter(absolutePath + new PropertyReader().getPropertyValue(propertyValue))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> read(String className) throws IOException {
        List<T> list;
        BufferedReader bufferedReader = null;
        String propertyValue = className + ".file.json";
        try {
            String absolutePath = new File("app/").getAbsolutePath();
            bufferedReader = new BufferedReader(new FileReader(absolutePath + new PropertyReader().getPropertyValue(propertyValue)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<T>>() {
        }.getType();
        list = gson.fromJson(bufferedReader, type);
        return list;
    }
}
