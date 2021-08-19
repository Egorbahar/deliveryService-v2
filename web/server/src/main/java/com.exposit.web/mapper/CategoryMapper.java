package com.exposit.web.mapper;

import com.exposit.persistence.entity.Category;
import com.exposit.web.dto.request.CategoryRequestDto;
import com.exposit.web.dto.request.ProductRequestDto;
import com.exposit.web.dto.response.CategoryResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toCategoryResponseDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryRequestDto categoryDto);
    @IterableMapping(elementTargetType = ProductRequestDto.class)
    List<CategoryResponseDto> toCategoryResponseDtoList(Collection<Category> categories);
}
