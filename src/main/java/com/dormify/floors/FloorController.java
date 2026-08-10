package com.dormify.floors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
public class FloorController {

    private final FloorService floorService;

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add floor to dormitory", description = "Adds a new floor to the specified dormitory.")
    @ApiResponse(responseCode = "201", description = "Floor added successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FloorDto.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content())
    @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
    @ApiResponse(responseCode = "404", description = "Dormitory not found", content = @Content())
    @PostMapping("/dormitories/{dormitoryId}/floors")
    public ResponseEntity<FloorDto> createFloor(
            @PathVariable("dormitoryId") Long dormitoryId,
            @RequestBody @Valid CreateFloorRequest request,
            UriComponentsBuilder uriBuilder) {
        var floor = floorService.createFloor(dormitoryId, request);
        var uri = uriBuilder.path("/floors/{id}").buildAndExpand(floor.getId()).toUri();
        return ResponseEntity.created(uri).body(floor);
    }
}
