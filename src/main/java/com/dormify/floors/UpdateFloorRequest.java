package com.dormify.floors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO for updating a floor")
public class UpdateFloorRequest {
    @Schema(description = "User ID of the person responsible for this floor", example = "2")
    private Long representativePersonId;
}
