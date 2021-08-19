package com.exposit.web.controller;

import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Product;
import com.exposit.web.dto.request.ProductRequestDto;
import com.exposit.web.dto.response.ProductResponseDto;
import com.exposit.web.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public void save(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        productService.addProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> productResponseDtoList = productMapper.toProductResponseDtoList(productService.getAll());
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("id") Long id) {
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(productService.findById(id));
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDto> update(@Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        productService.updateProduct(product);
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
}
