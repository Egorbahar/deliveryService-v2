package com.exposit.core.controller;

import com.exposit.core.dto.CategoryDto;
import com.exposit.core.exception.FormatFileException;
import com.exposit.core.mapper.CategoryMapper;
import com.exposit.core.model.Category;
import com.exposit.core.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public void save(@Valid @RequestBody CategoryDto categoryDto) {
        try {
            Category category = categoryMapper.toCategory(categoryDto);
            categoryService.save(category);
        } catch (FormatFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categoryDtoList = categoryMapper.toCategoryDTOs(categoryService.getAll());
        return new ResponseEntity<List<CategoryDto>>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long id) {
        CategoryDto categoryDto = categoryMapper.toCategoryDTO(categoryService.getById(id));
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        categoryService.update(category);
        return ResponseEntity.ok(categoryDto);
    }
}
