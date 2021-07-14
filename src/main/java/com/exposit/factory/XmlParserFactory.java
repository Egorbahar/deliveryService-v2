package com.exposit.factory;

public class XmlParserFactory implements ParserFactory{
    @Override
    public Worker createWorker() {
        return new XmlWorker();
    }
}
