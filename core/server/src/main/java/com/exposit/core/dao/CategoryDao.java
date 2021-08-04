package com.exposit.core.dao;

import com.exposit.core.exception.FormatFileException;
import com.exposit.core.model.Category;

import java.io.IOException;

public interface CategoryDao {
    void save(Category category) throws FormatFileException, IOException;
}
