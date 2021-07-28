package com.ex.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private List<Category> subCategories;
    private List<Product> products;

    public Category(String name, List<Category> subCategories, List<Product> products) {
        this.name = name;
        this.subCategories = subCategories;
        this.products = products;
    }
}
