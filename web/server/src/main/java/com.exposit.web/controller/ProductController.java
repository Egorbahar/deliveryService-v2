package com.exposit.web.controller;

import com.exposit.core.service.CategoryService;
import com.exposit.core.service.ProductService;
import com.exposit.persistence.entity.Category;
import com.exposit.persistence.entity.Product;
import com.exposit.web.annotation.LogExecutionTime;
import com.exposit.web.dto.request.ProductRequestDto;
import com.exposit.web.dto.response.ProductResponseDto;
import com.exposit.web.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    @LogExecutionTime
    public void save(@Valid @RequestBody ProductRequestDto productRequestDto) {
        List<Category> categories = categoryService.getAll().stream()
                                                   .filter(c -> productRequestDto.getCategories_id().contains(c.getId()))
                                                   .collect(Collectors.toList());
        Product product = productMapper.toProduct(productRequestDto);
        product.setCategories(categories);
        productService.save(product);
    }

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> productResponseDtoList = productMapper.toProductResponseDtoList(productService.getAll());
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        Product product = productService.findById(id);
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public void delete(@PathVariable("id") @NotBlank @Positive Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);
        product.setId(id);
        productService.update(product);
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
    @GetMapping("/categories/{id}/lessAvgPrice")
    @LogExecutionTime
    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryWithPriceLessAvr(@PathVariable("id") @NotBlank @Positive Long categoryId)
    {
        List<ProductResponseDto> productResponseDto = productMapper.toProductResponseDtoList(productService.findProductByCategoryIdWithPriceLessAvg(categoryId));
        return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
    }
    @GetMapping("/inStock")
    @LogExecutionTime
    public ResponseEntity<List<ProductResponseDto>> findByProductInStock(@Valid @RequestParam boolean isInStock) {
        List<ProductResponseDto> productResponseDtoList = productMapper.toProductResponseDtoList(productService.findProductInStock(isInStock));
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }
    @GetMapping("/categories/{id}")
    @LogExecutionTime
    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryId(@PathVariable("id") @NotBlank @Positive Long categoryId)
    {
        List<ProductResponseDto> productResponseDto = productMapper.toProductResponseDtoList(productService.findProductsByCategoryId(categoryId));
        return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
    }
}
