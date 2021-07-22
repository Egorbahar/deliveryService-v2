package com.exposit.service;

import com.exposit.exception.FormatFileException;
import com.exposit.model.Category;

import java.io.IOException;


public interface CategoryService {
    void save(Category category) throws FormatFileException, IOException;
}
