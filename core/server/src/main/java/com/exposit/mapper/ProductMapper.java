package com.exposit.mapper;

import com.exposit.dto.ProductDto;
import com.exposit.model.Product;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
//
//    ProductDto toProductDTO(Product product);
//
//    List<ProductDto> toProductDTOs(List<Product> products);
//
//    Product toProduct(ProductDto productDto);
    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);
    @BeanMapping()
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductDto productDto);
    @IterableMapping(elementTargetType = ProductDto.class)
    List<ProductDto> toProductDtoList(Collection<Product> products);
}

