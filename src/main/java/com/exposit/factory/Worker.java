package com.exposit.factory;

import java.util.List;

public interface Worker<T> {
    void write(String propertyName, List<T> list);

    List<T> read(String propertyName);
}
