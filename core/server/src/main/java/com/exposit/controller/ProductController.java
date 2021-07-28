package com.exposit.controller;

import com.exposit.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
//
//    @PostMapping
//    public void save(@Valid @RequestBody Product product) {
//
//    }
    @GetMapping
    public String list()
    {
        return "test";
    }
}
