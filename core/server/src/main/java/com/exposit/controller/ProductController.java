package com.exposit.controller;

import com.exposit.dto.ProductDto;
import com.exposit.exception.FormatFileException;
import com.exposit.mapper.ProductMapper;
import com.exposit.model.Product;
import com.exposit.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = productMapper.toProductDtoList(productService.getAll());
        return new ResponseEntity<List<ProductDto>>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        ProductDto productDto = productMapper.toProductDto(productService.getById(id));
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        productService.updateProduct(product);
        return ResponseEntity.ok(productDto);
    }
}
