package com.dormify.applications;

import com.dormify.common.FileMetaDataDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ApplicationDto {
    @Schema(description = "Unique identifier of the application", example = "1")
    private Long id;

    @Schema(description = "Faculty number of the user", example = "12345")
    private String facultyNumber;

    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "User's middle name", example = "A.")
    private String middleName;

    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @Schema(description = "User's last name", example = "Doe")
    private String phoneNumber;

    @Schema(description = "Type of the application", example = "APPLICATION_FIRST_COURSE_NOT_FAMILY")
    private ApplicationType type;

    @Schema(description = "Timestamp when this application was created",
            example = "2024-12-01T11:24:00")
    private LocalDateTime createdAt;

    @Schema(
            description = "List of metadata objects for the files attached to the application"
    )
    private Set<FileMetaDataDto> fileMetaData = new HashSet<>();

    @Schema(description = "Current status of the application", example = "PENDING")
    private ApplicationStatus status;

    @Schema(description = "If the student is government fundend", example = "true")
    private boolean isGovernmentFunded;

    @Schema(description = "Student's study form", example = "REGULAR")
    private StudyForm studyForm;

    @Schema(description = "Student's gpa", example = "5.50")
    private Double gpa;

    @Schema(description = "Indicates whether the application tax fee has been successfully paid", example = "true")
    private boolean taxFeePaid;

    @Schema(description = "Reference number the successful payment (if paid).", example = "PAY-202512-456")
    private String paymentNumber;
}
