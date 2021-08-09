package com.exposit.core.dao;

import com.exposit.core.model.Category;
import com.exposit.web.exception.FormatFileException;

import java.io.IOException;

public interface CategoryDao {
    void save(Category category) throws FormatFileException, IOException;
}
