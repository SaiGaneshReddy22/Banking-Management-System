package com.bank.bankingsystem.controller;

import com.bank.bankingsystem.dto.AccountRequestDto;
import com.bank.bankingsystem.dto.AccountResponseDto;
import com.bank.bankingsystem.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(
        name = "Account Management",
        description = "APIs for creating and managing customer bank accounts."
)
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountResponseDto createAccount(
            @Valid @RequestBody AccountRequestDto requestDto) {

        return accountService.createAccount(requestDto);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponseDto getAccountByNumber(
        @PathVariable String accountNumber) {

    return accountService.getAccountByNumber(accountNumber);
    }


    @GetMapping
    public List<AccountResponseDto> getAllAccounts() {

    return accountService.getAllAccounts();
}
}