package com.exposit.factory;

import com.exposit.util.PropertyReader;
import lombok.SneakyThrows;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class XmlWorker<T> implements Worker<T> {


    @SneakyThrows
    @Override
    public void write(String propertyName, List<T> list) {
        String absolutePath = new File("").getAbsolutePath();
        FileOutputStream fos = new FileOutputStream(absolutePath + new PropertyReader().getPropertyValue(propertyName));
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(list);
        encoder.close();
        fos.close();
    }


    @Override
    public List<T> read(String propertyName){
        String absolutePath = new File("").getAbsolutePath();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(absolutePath + new PropertyReader().getPropertyValue(propertyName));
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



