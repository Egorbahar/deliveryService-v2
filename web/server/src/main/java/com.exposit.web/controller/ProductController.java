package com.exposit.web.controller;

import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Product;
import com.exposit.web.dto.ProductDto;
import com.exposit.web.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public void save(@Valid @RequestBody ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        productService.addProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = productMapper.toProductDtoList(productService.getAll());
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        ProductDto productDto = productMapper.toProductDto(productService.findById(id));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        productService.updateProduct(product);
        return ResponseEntity.ok(productDto);
    }
}
