package com.ex.controller;

import com.ex.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    //private final ProductService productService;

    @PostMapping
    public void save(@Valid @RequestBody Product product) {

    }
}
