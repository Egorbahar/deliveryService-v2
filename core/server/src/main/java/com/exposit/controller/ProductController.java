package com.exposit.controller;

import com.exposit.dto.ProductDto;
import com.exposit.exception.FormatFileException;
import com.exposit.mapper.ProductMapper;
import com.exposit.model.Product;
import com.exposit.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    public void save(@Valid @RequestBody ProductDto productDto) {
        try {
            Product product = productMapper.toProduct(productDto);
            productService.addProduct(product);
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<ProductDto> getAll() {
        try {
            return productMapper.toProductDtoList(productService.getAll());
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @DeleteMapping(value = "{id}")
//    public void delete(@PathVariable Long id) {
//        productService.deleteProduct()
//    }
}
