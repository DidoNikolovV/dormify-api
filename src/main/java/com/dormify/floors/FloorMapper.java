package com.dormify.floors;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FloorMapper {

    Floor toEntity(FloorDto dto);

    FloorDto toDto(Floor entity);
}
