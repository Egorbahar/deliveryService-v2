package com.exposit.model;

import com.exposit.enums.CategoryItem;
import lombok.Data;

@Data
public class Category {
    CategoryItem categoryItem;
    Long productId;

    public Category(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

}
