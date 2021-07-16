package com.exposit.util;

import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyReader {
    @SneakyThrows
    public String getPropertyValue(String propertyName) {
        Properties prop = new Properties();
        String propFileName = "application.properties";
        //URL url = new URL("C:\\Users\\User\\IdeaProjects\\deliveryService-v2\\ModuleLogic\\src\\main\\resources\\application.properties");
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("C:\\Users\\User\\IdeaProjects\\deliveryService-v2\\ModuleLogic\\src\\main\\resources\\application.properties")){

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }
}
