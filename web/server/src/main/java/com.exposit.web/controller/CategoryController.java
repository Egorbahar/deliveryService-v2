package com.exposit.web.controller;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.web.annotation.LogExecutionTime;
import com.exposit.web.dto.request.CategoryRequestDto;
import com.exposit.web.dto.response.CategoryProductCountResponseDto;
import com.exposit.web.dto.response.CategoryResponseDto;
import com.exposit.web.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @LogExecutionTime
    public void save(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Executing the category save method...");
        Category category = new Category();
        if (categoryRequestDto.getParent_category_id() == null) {
            category.setName(categoryRequestDto.getName());
        } else {
            category = categoryMapper.toCategory(categoryRequestDto);
            Category parentCategory = categoryService.findById(categoryRequestDto.getParent_category_id());
            category.setParent(parentCategory);
        }
        categoryService.save(category);
    }

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        log.info("Executing the category getAll method...");
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.getAll());
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        log.info("Executing the category getById method...");
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(categoryService.findById(id));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public void delete(@PathVariable("id") @NotBlank @Positive Long id) {
        log.info("Executing the category delete method...");
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Executing the category update method...");
        Category category = categoryMapper.toCategory(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/subcategories")
    @LogExecutionTime
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesByParentId(@PathVariable("id") @NotBlank @Positive Long id) {
        log.info("Executing the category  getCategoriesByParentId method...");
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.findCategoriesByParentId(id));
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/countProducts")
    @LogExecutionTime
    public ResponseEntity<CategoryProductCountResponseDto> getCountProductsByCategoryName(@Valid @RequestParam String categoryName) {
        log.info("Executing the category getCountProductsByCategoryName method...");
        Integer countProducts = categoryService.findCountProductByCategoryName(categoryName);
        CategoryProductCountResponseDto categoryProductCountResponseDto = new CategoryProductCountResponseDto();
        categoryProductCountResponseDto.setCountProduct(countProducts);
        categoryProductCountResponseDto.setCategoryName(categoryName);
        return new ResponseEntity<>(categoryProductCountResponseDto, HttpStatus.OK);
    }
}
