package com.dormify.applications;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@JsonSubTypes({
        @JsonSubTypes.Type(value = ApplicationFirstCourseDto.class, name = "APPLICATION_FIRST_COURSE_NOT_FAMILY"),
        @JsonSubTypes.Type(value = ApplicationUpperCourseDto.class, name = "APPLICATION_UPPER_COURSE_NOT_FAMILY"),
        @JsonSubTypes.Type(value = MarriedApplicationDto.class, name = "APPLICATION_FIRST_COURSE_FAMILY"),
        @JsonSubTypes.Type(value = MarriedApplicationDto.class, name = "APPLICATION_UPPER_COURSE_FAMILY"),
        @JsonSubTypes.Type(value = RelocationApplicationDto.class, name = "APPLICATION_UPPER_COURSE_RELOCATION"),
        @JsonSubTypes.Type(value = NightlyApplicationRequestDto.class, name = "NIGHTLY")
})
public class CreateApplicationBaseRequest {
    @Schema(description = "Type of the application", example = "APPLICATION_FIRST_COURSE_NOT_FAMILY",
            allowableValues = {"APPLICATION_FIRST_COURSE_NOT_FAMILY", "APPLICATION_UPPER_COURSE_NOT_FAMILY",
                    "APPLICATION_UPPER_COURSE_FAMILY", "APPLICATION_FIRST_COURSE_FAMILY", "APPLICATION_UPPER_COURSE_RELOCATION", "NIGHTLY"})
    @NotNull(message = "Type is required")
    private ApplicationType type;

    @NotBlank(message = "First name is required")
    @Length(min = 1, max = 255, message = "First name must be between 1 and 255 characters long")
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Length(max = 255, message = "Middle name must be at most 255 characters long")
    @Schema(description = "User's middle name", example = "Michael")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Length(min = 1, max = 255, message = "Last name must be between 1 and 255 characters long")
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "User's phone number in Bulgarian format", example = "+359888123456")
    private String phoneNumber;

    @Email(message = "Invalid email")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
}
