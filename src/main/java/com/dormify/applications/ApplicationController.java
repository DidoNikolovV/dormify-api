package com.dormify.applications;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(
            summary = "Submit application",
            description = "Submit application to be accepted for a dormitory"
    )
    @ApiResponse(responseCode = "201", description = "Application submitted successfully",
            content = @Content(schema = @Schema(implementation = ApplicationDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content())
    @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content())
    @ApiResponse(responseCode = "404", description = "Application window not found", content = @Content())
    @PostMapping(value = "/windows/{windowId}/applications", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApplicationDto> submitApplication(@PathVariable("windowId") Long windowId,
                                                            @Valid @RequestPart("application") CreateApplicationRequest request,
                                                            @Valid @ModelAttribute DocumentsWrapper documents,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            UriComponentsBuilder uriBuilder) {
        var application = applicationService.submitApplication(windowId, request, documents, userDetails.getUsername());
        var uri = uriBuilder.path("/windows/{windowId}/applications/{applicationId}").buildAndExpand(windowId, application.getId()).toUri();
        return ResponseEntity.created(uri).body(application);
    }
}
