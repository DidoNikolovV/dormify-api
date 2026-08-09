package com.dormify.dormitories;

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

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete dormitory by ID", description = "Deletes a dormitory based on the provided ID.")
    @ApiResponse(responseCode = "204", description = "Dormitory deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content())
    @ApiResponse(responseCode = "404", description = "Dormitory not found", content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDormitory(@PathVariable("id") Long id) {
        dormitoryService.deleteDormitory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "View all dormitories", description = "Retrieves a paginated list of all dormitories.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dormitories",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PagingResult.class)))
    @GetMapping
    public ResponseEntity<PagingResult<DormitoryDto>> getDormitories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        final PaginationRequest request = new PaginationRequest(page, size, sortField, direction);
        return ResponseEntity.ok(dormitoryService.getDormitories(request));
    }
}
