package com.dormify.applications;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ApplicationUpperCourseDto extends AcademicApplicationDto {
    @Length(max = 64, message = "DormitoryName should be between 2 and 64 characters long.")
    @Schema(description = "Name of the dormitory", example = "Green Hall")
    private String dormitoryName;

    @Length(max = 64, message = "Room Number should be at most 64 characters long.")
    @Schema(description = "Current room number", example = "405A")
    private String roomNumber;
}
