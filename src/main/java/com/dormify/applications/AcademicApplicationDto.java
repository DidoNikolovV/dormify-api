package com.dormify.applications;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AcademicApplicationDto extends CreateApplicationBaseRequest {
    @NotBlank(message = "Faculty number is required")
    @Length(min = 1, max = 15, message = "Faculty number must be between 1 and 15 characters long")
    @Schema(description = "Student's faculty number", example = "1234567890")
    private String facultyNumber;

    @NotBlank(message = "Faculty is required")
    @Length(min = 1, max = 255, message = "Faculty name is required")
    @Schema(description = "Faculty in which the student is studying", example = "Faculty of Computer Science")
    private String faculty;

    @NotBlank(message = "Specialty is required")
    @Length(min = 1, max = 255, message = "Specialty name is required")
    @Schema(description = "Student's major or specialty", example = "Software Engineering")
    private String specialty;

    @NotBlank(message = "Course is required")
    @Length(max = 50, message = "Course must not exceed 50 characters")
    @Schema(description = "The current course of study", example = "3")
    private String course;
}
