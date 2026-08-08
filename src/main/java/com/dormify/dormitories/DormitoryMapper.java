package com.dormify.dormitories;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DormitoryMapper {

    Dormitory toEntity(CreateDormitoryRequest dto);

    DormitoryDto toDto(Dormitory entity);
}
