package com.bank.bankingsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponseDto {

    @Schema(
            description = "Unique identifier of the user",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Customer full name",
            example = "Aftab Ahmed"
    )
    private String fullName;

    @Schema(
            description = "Registered email",
            example = "aftab@gmail.com"
    )
    private String email;

    @Schema(
            description = "Role assigned to the user",
            example = "CUSTOMER"
    )
    private String role;



    
}
