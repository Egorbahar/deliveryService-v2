package com.exposit.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public String getPropertyValue(String propertyName) {
        Properties prop = new Properties();
        String propFileName = "application.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);) {

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
