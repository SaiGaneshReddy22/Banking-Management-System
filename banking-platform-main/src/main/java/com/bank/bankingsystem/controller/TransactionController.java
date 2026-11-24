package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.dto.TransactionResponseDto;
import com.bank.bankingsystem.dto.TransferRequestDto;
import com.bank.bankingsystem.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(
        name = "Transaction Management",
        description = "APIs for money transfer, deposits, withdrawals, and transaction history."
)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public String transferMoney(
            @Valid @RequestBody TransferRequestDto requestDto) {

        return transactionService.transferMoney(requestDto);
    }

    @GetMapping
    public List<TransactionResponseDto> getAllTransactions() {

    return transactionService.getAllTransactions();
    }

  

    @GetMapping("/account/{accountNumber}")
    public Page<TransactionResponseDto> getTransactionsByAccount(@PathVariable String accountNumber,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {

         return transactionService
            .getTransactionsByAccount(
                    accountNumber,
                    page,
                    size);
    }




}