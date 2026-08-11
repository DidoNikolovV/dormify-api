package com.dormify.applications;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {


    Application toEntityFromFirstCourse(ApplicationFirstCourseDto firstDto);

    Application toEntityFromMarriedApplication(MarriedApplicationDto dto);

    @Mapping(target = "type", source = "type")
    @SubclassMapping(source = MarriedApplicationDto.class, target = Application.class)
    @SubclassMapping(source = RelocationApplicationDto.class, target = Application.class)
    Application toEntity(AcademicApplicationDto dto);
}
