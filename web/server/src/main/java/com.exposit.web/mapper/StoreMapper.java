package com.exposit.web.mapper;

import com.exposit.persistence.entity.Store;
import com.exposit.web.dto.request.StoreRequestDto;
import com.exposit.web.dto.response.StoreResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreResponseDto toStoreResponseDto(Store store);

    @Mapping(target = "id", ignore = true)
    Store toStore(StoreRequestDto storeRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Store store, StoreRequestDto storeDto);

    @IterableMapping(elementTargetType = StoreResponseDto.class)
    List<StoreResponseDto> toStoreResponseDtoList(Collection<Store> stores);
}
