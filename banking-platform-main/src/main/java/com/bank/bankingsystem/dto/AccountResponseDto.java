package com.bank.bankingsystem.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponseDto {

    @Schema(
            description = "Generated account number",
            example = "ACC458721"
    )
    private String accountNumber;

    private String accountType;

    @Schema(
            description = "Available account balance",
            example = "25000.00"
    )
    private BigDecimal balance;

    @Schema(
            description = "Current account status",
            example = "ACTIVE"
    )
    private String status;

    private String customerName;
}