package com.ex.dao;

import com.ex.exception.FormatFileException;
import com.ex.model.Category;

import java.io.IOException;

public interface CategoryDao {
    void save(Category category) throws FormatFileException, IOException;
}
