package com.dormify.floors;

import com.dormify.common.PaginationRequest;
import com.dormify.common.PagingResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Get floor details by ID", description = "Retrieves floor details.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved floor",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FloorDto.class)))
    @ApiResponse(responseCode = "404", description = "Floor not found", content = @Content())
    @GetMapping("/{id}")
    public ResponseEntity<FloorDto> getFloor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.getFloor(id));
    }

    @Operation(summary = "Get floors in dormitory", description = "Retrieves a paginated list of floors associated with a dormitory.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved floors",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PagingResult.class)))
    @ApiResponse(responseCode = "404", description = "Dormitory not found", content = @Content())
    @GetMapping("/{dormitoryId}/floors")
    public ResponseEntity<PagingResult<FloorDto>> getFloors(@PathVariable("dormitoryId") Long dormitoryId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "id") String sortField,
                                                            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        final PaginationRequest request = new PaginationRequest(page, size, sortField, direction);
        return ResponseEntity.ok(floorService.getFloorsByDormitory(dormitoryId, request));
    }
}
