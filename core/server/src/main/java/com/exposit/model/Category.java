package com.exposit.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private Long id;
    private String name;
    private List<Category> subCategories;
    private List<Product> products;


    public Category(){

    }
}
