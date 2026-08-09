package com.dormify.dormitories;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/dormitories")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    @PostMapping
    public ResponseEntity<DormitoryDto> createDormitory(
            @RequestBody @Valid CreateDormitoryRequest request,
            UriComponentsBuilder uriBuilder) {
        var dormitory = dormitoryService.createDormitory(request);
        var uri = uriBuilder.path("/dormitories/{id}").buildAndExpand(dormitory.getId()).toUri();
        return ResponseEntity.created(uri).body(dormitory);
    }

    @Operation(summary = "Get dormitory details by ID", description = "Retrieves detailed information about a specific dormitory.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dormitory",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DormitoryDto.class)))
    @ApiResponse(responseCode = "404", description = "Dormitory not found", content = @Content())
    @GetMapping("/{id}")
    public ResponseEntity<DormitoryDto> getDormitory(@PathVariable Long id) {
        return ResponseEntity.ok(dormitoryService.getDormitoryById(id));
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update dormitory details", description = "Updates details of an existing dormitory.")
    @ApiResponse(responseCode = "200", description = "Dormitory updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content())
    @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
    @ApiResponse(responseCode = "404", description = "Dormitory not found", content = @Content())
    @PatchMapping("/{id}")
    public ResponseEntity<DormitoryDto> updateDormitory(@PathVariable("id") Long id,
                                                        @RequestBody @Valid UpdateDormitoryRequest request) {
        return ResponseEntity.ok(dormitoryService.updateDormitory(id, request));
    }
}
