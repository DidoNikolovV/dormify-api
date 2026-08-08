package com.dormify.dormitories;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Schema(description = "DTO for creating a dormitory")
public class CreateDormitoryRequest {
    @NotBlank(message = "Name is required")
    @Length(min = 1, max = 255, message = "Name should be between 1 and 255 characters long")
    @Schema(description = "Name of the dormitory", example = "Green Hall")
    private String name;

    @Schema(description = "ID of the manager assigned to this dormitory", example = "1")
    private Long managerId;

    @NotNull(message = "Capacity is required")
    @Max(value = 1500, message = "Capacity cannot exceed 1500")
    @Schema(description = "Determines how much is the capacity in the dormitory", example = "100")
    private Integer capacity;
}
