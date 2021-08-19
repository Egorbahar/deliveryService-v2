package com.exposit.web.mapper;

import com.exposit.persistence.entity.Product;
import com.exposit.web.dto.request.ProductRequestDto;
import com.exposit.web.dto.response.ProductResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequestDto productRequestDto);

    ProductResponseDto toProductResponseDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductRequestDto productDto);

    @IterableMapping(elementTargetType = ProductRequestDto.class)
    List<ProductResponseDto> toProductResponseDtoList(Collection<Product> products);
}

