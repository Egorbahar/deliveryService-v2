package com.exposit.web.controller;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.web.dto.request.CategoryRequestDto;
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
    public void save(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        categoryService.save(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.getAll());
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(categoryService.findById(id));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @NotBlank @Positive Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
}
