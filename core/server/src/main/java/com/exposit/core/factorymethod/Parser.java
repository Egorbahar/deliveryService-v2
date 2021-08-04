package com.exposit.core.factorymethod;

import java.io.IOException;
import java.util.List;

public interface Parser<T> {
    void write(String className, List<T> list);

    List<T> read(String className) throws IOException;
}
