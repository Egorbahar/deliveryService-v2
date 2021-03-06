package com.exposit.core.factorymethod;

import com.exposit.core.exception.FormatFileException;
import com.exposit.core.util.PropertyReader;

import java.io.IOException;

public class ParserFactory {
    public Parser getParser() throws FormatFileException, IOException {
        String format;
        format = new PropertyReader().getPropertyValue("file.format");
        if (format.equals("XML")) {
            return new XmlParser<>();
        } else if (format.equals("JSON")) {
            return new JsonParser<>();
        } else {
                throw new FormatFileException("Format not supported");
        }
    }
}
