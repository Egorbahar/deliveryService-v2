package com.exposit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class Category {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    private List<Category> subCategories;
    private List<Product> products;

    public Category(String name, List<Category> subCategories, List<Product> products) {
        this.name = name;
        this.subCategories = subCategories;
        this.products = products;
    }
}
