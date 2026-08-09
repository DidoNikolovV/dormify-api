package com.dormify.floors;

import com.dormify.rooms.OccupancyType;
import com.dormify.rooms.RoomStatus;
import com.dormify.rooms.RoomType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO for a Room")
public class RoomDto {
    @Schema(description = "Room identifier", example = "1")
    private Long id;

    @Schema(description = "Room number", example = "101")
    private String number;

    @Schema(description = "Room occupancy type", example = "DOUBLE")
    private OccupancyType occupancyType;

    @Schema(description = "Room status", example = "NON_OCCUPIED")
    private RoomStatus status;

    @Schema(description = "Dormitory identifier where the room is located", example = "1")
    private Long dormitoryId;

    @Schema(description = "Floor number where the room is located", example = "1")
    private int floorNumber;

    @Schema(description = "Floor identifier", example = "10")
    private Long floorId;

    @Schema(description = "Type of the room", example = "LAUNDRY")
    private RoomType type;
}
