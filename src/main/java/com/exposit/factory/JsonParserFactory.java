package com.exposit.factory;


public class JsonParserFactory implements ParserFactory {
    @Override
    public Worker createWorker() {
        return  new JsonWorker();
    }
}
