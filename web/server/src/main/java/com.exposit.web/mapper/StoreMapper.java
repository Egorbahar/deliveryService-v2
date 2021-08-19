package com.exposit.web.mapper;

import com.exposit.persistence.entity.Store;
import com.exposit.web.dto.StoreDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store toStore(StoreDto productDto);

    StoreDto toStoreDto(Store product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Store store, StoreDto storeDto);

    @IterableMapping(elementTargetType = StoreDto.class)
    List<StoreDto> toStoreDtoList(Collection<Store> stores);
}
