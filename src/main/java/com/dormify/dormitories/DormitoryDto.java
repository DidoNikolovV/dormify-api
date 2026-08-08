package com.dormify.dormitories;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DormitoryDto {
    @Schema(description = "Unique identifier of the dormitory", example = "1")
    private int id;

    @Schema(description = "Name of the dormitory", example = "Green Hall")
    private String name;

    @Schema(description = "ID of the dormitory manager", example = "1")
    private Long managerId;

    @Schema(description = "Maximum capacity of the dormitory", example = "200")
    private Integer capacity;

    @Schema(description = "Available places in the dormitory", example = "100")
    private int availablePlaces;
}
