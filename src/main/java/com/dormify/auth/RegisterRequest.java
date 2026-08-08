package com.dormify.auth;

import com.dormify.users.Gender;
import com.dormify.users.IdType;
import com.dormify.users.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterRequest {

    @Length(max = 15, message = "Faculty number must be between 1 and 15 characters long")
    @Schema(description = "Student's faculty number", example = "1234567890")
    private String facultyNumber;

    @NotBlank(message = "National ID is required")
    @Length(min = 1, max = 20, message = "National ID must be between 1 and 20 characters long")
    @Schema(description = "National ID of the user", example = "1234567890")
    private String nationalId;

    @NotNull(message = "ID type is required")
    @Schema(description = "Type of the national ID", example = "EGN", allowableValues = {"EGN", "LNC", "PASSPORT"})
    private IdType idType;

    @Schema(description = "User's first name", example = "John")
    @NotBlank(message = "First name is required")
    @Length(min = 1, max = 255, message = "First name must be between 1 and 255 characters long")
    private String firstName;

    @Schema(description = "User's middle name", example = "Michael")
    @Length(max = 255, message = "Middle name must be at most 255 characters long")
    private String middleName;

    @Schema(description = "User's last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    @Length(min = 1, max = 255, message = "Last name must be between 1 and 255 characters long")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "User's phone number", example = "+359888123456")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    @Schema(description = "Role of the user (e.g., STUDENT, GUEST)",
            example = "STUDENT",
            allowableValues = {"STUDENT", "GUEST"})
    private Role role;

    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 64, message = "Password should be between 8 and 64 characters long")
    @Schema(
            description = "User's password. Must include uppercase, lowercase, numbers, and special characters for security.",
            example = "Str0ngP@ssw0rd!",
            minLength = 8,
            maxLength = 64
    )
    private String password;

    @Schema(description = "Gender of the user",
            example = "MALE",
            allowableValues = {"NOT_APPLICABLE", "MALE", "FEMALE", "NOT_KNOWN"})
    private Gender gender;
}
