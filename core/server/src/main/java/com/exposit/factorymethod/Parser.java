package com.exposit.factorymethod;

import java.util.List;

public interface Parser<T> {
    void write(String className, List<T> list);

    List<T> read(String className);
}
