package com.exposit.dao;

import com.exposit.exception.FormatFileException;
import com.exposit.model.Category;

import java.io.IOException;

public interface CategoryDao {
    void save(Category category) throws FormatFileException, IOException;
}
