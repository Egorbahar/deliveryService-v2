package com.exposit.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Long id;
    private String name;
    private Integer quantity;
    private Store store;
    private List<Category> categories;
    private Double price;

    public Product(String name, Integer quantity, List<Category> categories, Double price, Store store) {
        this.name = name;
        this.quantity = quantity;
        this.categories = categories;
        this.price = price;
        this.store = store;
    }

    public Product(Long id) {
        this.id = id;
    }

    public String valueOfPrice() {
        return String.valueOf(price);
    }

}
