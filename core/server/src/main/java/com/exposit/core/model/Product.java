package com.exposit.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor

public class Product {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    @Size(min = 2, max = 30)
    private Integer quantity;
    @NotNull
    @Min(0)
    private Double price;
    private List<Store> stores;
    private List<Category> categories;


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
