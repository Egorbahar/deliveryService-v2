package com.exposit.core.mapper;

import com.exposit.core.dto.CategoryDto;
import com.exposit.core.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toCategoryDTO(Category category);

    List<CategoryDto> toCategoryDTOs(List<Category> categories);

    Category toCategory(CategoryDto categoryDto);
}
