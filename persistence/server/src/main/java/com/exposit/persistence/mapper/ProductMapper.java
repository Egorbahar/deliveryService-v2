package com.exposit.persistence.mapper;

import com.exposit.core.dto.ProductDto;
import com.exposit.core.model.Product;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    @BeanMapping()
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductDto productDto);

    @IterableMapping(elementTargetType = ProductDto.class)
    List<ProductDto> toProductDtoList(Collection<Product> products);
}

