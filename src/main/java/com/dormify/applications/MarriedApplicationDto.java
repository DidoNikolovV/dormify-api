package com.dormify.applications;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MarriedApplicationDto extends AcademicApplicationDto {
    @NotBlank(message = "Spouse's first name is required")
    @Length(min = 1, max = 255, message = "Spouse's first name must be between 1 and 255 characters long")
    @Schema(description = "Spouse's first name (Conditional, see isMarried flag)", example = "Jane")
    private String spouseFirstName;

    @Length(max = 255, message = "Spouse middle name must be at most 255 characters long")
    @Schema(description = "Spouse's middle name (Conditional, see isMarried flag)", example = "Amanda")
    private String spouseMiddleName;

    @NotBlank(message = "Spouse's last name is required")
    @Length(min = 1, max = 255, message = "Spouse's last name must be between 1 and 255 characters long")
    @Length(max = 255, message = "Spouse last name must at most 255 characters long")
    @Schema(description = "Spouse's last name (Conditional, see isMarried flag)", example = "Doe")
    private String spouseLastName;
}
