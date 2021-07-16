package com.exposit.util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {

    @SneakyThrows
    public String getPropertyValue(String propertyName) {
        Properties prop = new Properties();
        String absolutePath = new File("src/main/resources/application.properties").getAbsolutePath();
        try {
            File file = new File(absolutePath);
            prop.load(new FileInputStream(file));
            return prop.getProperty(propertyName);
        }catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("property file '" + absolutePath + "' not found in the classpath");
        }
    }
}
