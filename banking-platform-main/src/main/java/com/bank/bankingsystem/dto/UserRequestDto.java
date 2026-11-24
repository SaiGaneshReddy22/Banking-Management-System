package com.bank.bankingsystem.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {

    @Schema(
            description = "Customer's full name",
            example = "Aftab Ahmed"
    )
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Schema(
            description = "Registered email address",
            example = "aftab@gmail.com"
    )
    @Email(message = " Invalid email format")
    private String email;


    @Schema(
            description = "User login password",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    private String password;

}

