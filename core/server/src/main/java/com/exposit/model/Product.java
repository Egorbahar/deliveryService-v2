package com.exposit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Integer quantity;
    private List<Store> stores;
    private List<Category> categories;
    private Double price;

    public Product(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
//        this.categories = categories;
        this.price = price;
//        this.stores = stores;
    }

    public Product(Long id) {
        this.id = id;
    }

    public String valueOfPrice() {
        return String.valueOf(price);
    }

}
