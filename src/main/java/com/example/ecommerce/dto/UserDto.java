package com.example.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id; // User ID
    @NotEmpty
    private String firstName; // First name of the user
    @NotEmpty
    private String lastName; // Last name of the user
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email; // Email of the user, validated to ensure it's not empty and in email format
    @NotEmpty(message = "Password should not be empty")
    private String password; // Password of the user, validated to ensure it's not empty
}
