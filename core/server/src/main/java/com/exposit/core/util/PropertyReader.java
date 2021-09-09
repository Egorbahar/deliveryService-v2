package com.exposit.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public String getPropertyValue(String propertyName) throws IOException {
        Properties prop = new Properties();
        String absolutePath = new File("app/src/main/resources/application.properties").getAbsolutePath();
        try {
            File file = new File(absolutePath);
            prop.load(new FileInputStream(file));
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println("property file '" + absolutePath + "' not found in the classpath");
            throw e;
        }
    }
}
