package com.exposit.web.mapper;

import com.exposit.persistence.entity.Category;
import com.exposit.web.dto.CategoryDto;
import com.exposit.web.dto.ProductDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryDto categoryDto);
    @IterableMapping(elementTargetType = ProductDto.class)
    List<CategoryDto> toCategoryDtoList(Collection<Category> categories);
}
