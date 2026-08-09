package com.dormify.floors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Detailed floor DTO including rooms")
public class FloorDto {
    @Schema(description = "Unique identifier of the floor", example = "1")
    private Long id;

    @Schema(description = "Floor number within the dormitory", example = "1")
    private int number;

    @Schema(description = "Total number of rooms on this floor", example = "10")
    private int totalNumberOfRooms;

    @Schema(description = "User ID of the responsible person", example = "2")
    private Long responsiblePersonId;

    @Schema(description = "List of rooms on this floor")
    private List<RoomDto> rooms = new ArrayList<>();

    @Schema(description = "Dormitory ID this floor belongs to", example = "1")
    private Long dormitoryId;
}
