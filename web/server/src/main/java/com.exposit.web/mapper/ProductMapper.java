package com.exposit.web.mapper;

import com.exposit.persistence.entity.Product;
import com.exposit.web.dto.ProductDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductDto productDto);

    @IterableMapping(elementTargetType = ProductDto.class)
    List<ProductDto> toProductDtoList(Collection<Product> products);
}

