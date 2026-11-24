package com.bank.bankingsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDto {

    @Schema(
            description = "Type of bank account",
            example = "SAVINGS"
    )
    @NotBlank(message = "Account type is required")
    private String accountType;

    @Schema(
            description = "Initial deposit amount",
            example = "25000"
    )
    @Min(value = 0, message = "Initial balance cannot be negative")
    private BigDecimal initialBalance;
}