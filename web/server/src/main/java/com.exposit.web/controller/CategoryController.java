package com.exposit.web.controller;

import com.exposit.core.service.CategoryService;
import com.exposit.persistence.entity.Category;
import com.exposit.web.dto.CategoryDto;
import com.exposit.web.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public void save(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        categoryService.save(category);
    }
    @GetMapping
    public String get()
    {
        return "test";
    }
//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> getAll() {
//        List<CategoryDto> categoryDtoList = categoryMapper.toCategoryDtoList(categoryService.getAll());
//        return new ResponseEntity<List<CategoryDto>>(categoryDtoList, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long id) {
        CategoryDto categoryDto = categoryMapper.toCategoryDTO(categoryService.findById(id));
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
