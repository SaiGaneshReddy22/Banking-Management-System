package com.bank.bankingsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {

    private String fromAccount;

    private String toAccount;

    private BigDecimal amount;

    @Schema(
            description = "Transaction type",
            example = "TRANSFER"
    )
    private String transactionType;

    @Schema(
            description = "Transaction status",
            example = "SUCCESS"
    )
    private String status;

    @Schema(
            description = "Transaction timestamp",
            example = "2026-06-29T18:30:00"
    )
    private LocalDateTime transactionTime;
}