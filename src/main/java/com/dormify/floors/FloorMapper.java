package com.dormify.floors;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FloorMapper {

    Floor toEntity(FloorDto dto);
    Floor toEntity(CreateFloorRequest request);

    FloorDto toDto(Floor entity);
}
