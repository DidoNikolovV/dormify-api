package com.dormify.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "User information including authentication token")
public class UserDto {
    @Schema(description = "User identifier", example = "1")
    private Long id;

    @Schema(description = "National id (personal ID number)", example = "1234567890")
    private String nationalId;

    @Schema(description = "Student's faculty number", example = "201030")
    private String facultyNumber;

    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "User's middle name", example = "Michael")
    private String middleName;

    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @Schema(description = "User's phone number", example = "+359888123456")
    private String phoneNumber;

    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Name of the dormitory the user is assigned to", example = "Green Hall")
    private String dormitoryName;

    @Schema(description = "Room number the user is assigned to", example = "101")
    private String roomNumber;

    @Schema(description = "JWT token for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Gender of the user", example = "MALE")
    private Gender gender;

    @Schema(description = "Roles assigned to the user")
    private List<Role> roles = new ArrayList<>();
}
