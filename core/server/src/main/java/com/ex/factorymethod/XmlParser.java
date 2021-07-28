package com.ex.factorymethod;

import com.ex.util.PropertyReader;
import lombok.SneakyThrows;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class XmlParser<T> implements Parser<T> {
    @SneakyThrows
    @Override
    public void write(String className, List<T> list) {
        String propertyValue = className + ".file.xml";
        String absolutePath = new File("").getAbsolutePath();
        FileOutputStream fos = new FileOutputStream(absolutePath + new PropertyReader().getPropertyValue(propertyValue));
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :" + e.toString());
            }
        });
        encoder.writeObject(list);
        encoder.close();
        fos.close();
    }

    @Override
    public List<T> read(String className) throws IOException {
        String absolutePath = new File("").getAbsolutePath();
        FileInputStream fis = null;
        String propertyValue = className + ".file.xml";
        try {
            fis = new FileInputStream(absolutePath + new PropertyReader().getPropertyValue(propertyValue));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XMLDecoder decoder = new XMLDecoder(fis);
        List<T> decoded = (List<T>) decoder.readObject();
        decoder.close();
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decoded;
    }
}
