package com.exposit.factorymethod;

import com.exposit.util.PropertyReader;

public class ParserFactory {
    public Parser getParser() {
        String format = new PropertyReader().getPropertyValue("file.format");
        if (format.equals("XML")) {
            return new XmlParser<>();
        } else if (format.equals("JSON")) {
            return new JsonParser<>();
        }
        return null;
    }
}
