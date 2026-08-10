package com.dormify.floors;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO for creating a floor")
public class CreateFloorRequest {
    @Min(value = 1, message = "Floor number must be at least 1")
    @Max(value = 10, message = "Floor number cannot exceed 10")
    @Schema(description = "Floor number within the dormitory", example = "1")
    private int number;

    @Max(value = 30, message = "Total number of rooms cannot exceed 30")
    @Schema(description = "Number of rooms on this floor", example = "10")
    private int totalNumberOfRooms = 0;

    @Schema(description = "User ID of the person responsible for this floor", example = "2")
    private Long representativePersonId;
}
