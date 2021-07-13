package com.exposit.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Store {
    private Long id;
    private String name;
    private String address;
    private List<Product> products;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.products = new ArrayList<>();
    }

}
