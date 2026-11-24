package com.bank.bankingsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {

    @Schema(
            description = "Sender account number",
            example = "ACC100001"
    )
    @NotBlank
    private String fromAccount;

    @Schema(
            description = "Receiver account number",
            example = "ACC100002"
    )
    @NotBlank
    private String toAccount;

    @Schema(
            description = "Amount to transfer",
            example = "5000"
    )
    @NotNull
    private BigDecimal amount;
}