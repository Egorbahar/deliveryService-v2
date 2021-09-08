package com.exposit.web.controller;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.web.annotation.LogExecutionTime;
import com.exposit.web.dto.request.CategoryRequestDto;
import com.exposit.web.dto.response.CategoryProductCountResponseDto;
import com.exposit.web.dto.response.CategoryResponseDto;
import com.exposit.web.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @LogExecutionTime
    public void save(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
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
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.getAll());
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(categoryService.findById(id));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public void delete(@PathVariable("id") @NotBlank @Positive Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/subcategories")
    @LogExecutionTime
    public ResponseEntity<List<CategoryResponseDto>> getCategoriesByParentId(@PathVariable("id") @NotBlank @Positive Long id) {
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.findCategoriesByParentId(id));
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }
    @GetMapping("/countProducts")
    @LogExecutionTime
    public ResponseEntity<CategoryProductCountResponseDto> getCountProductsByCategoryName(@Valid @RequestParam String categoryName)
    {
        Integer countProducts = categoryService.findCountProductByCategoryName(categoryName);
        CategoryProductCountResponseDto categoryProductCountResponseDto = new CategoryProductCountResponseDto();
        categoryProductCountResponseDto.setCountProduct(countProducts);
        categoryProductCountResponseDto.setCategoryName(categoryName);
        return new ResponseEntity<>(categoryProductCountResponseDto, HttpStatus.OK);
    }
}
