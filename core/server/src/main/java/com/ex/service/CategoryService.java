package com.ex.service;

import com.ex.exception.FormatFileException;
import com.ex.model.Category;

import java.io.IOException;


public interface CategoryService {
    void save(Category category) throws FormatFileException, IOException;
}
