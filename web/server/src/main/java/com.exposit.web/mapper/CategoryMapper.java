package com.exposit.web.mapper;

import com.exposit.persistence.entity.Category;
import com.exposit.web.dto.request.CategoryRequestDto;
import com.exposit.web.dto.response.CategoryResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CategoryMapper {
    @Mapping(source = "parent.id", target = "parent_category_id")
    CategoryResponseDto toCategoryResponseDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "parent_category_id", target = "parent.id")
    Category toCategory(CategoryRequestDto categoryRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryRequestDto categoryDto);

    @IterableMapping(elementTargetType = CategoryResponseDto.class)
    List<CategoryResponseDto> toCategoryResponseDtoList(Collection<Category> categories);
}
