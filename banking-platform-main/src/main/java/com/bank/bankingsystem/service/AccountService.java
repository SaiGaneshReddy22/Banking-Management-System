package com.bank.bankingsystem.service;

import com.bank.bankingsystem.dto.AccountRequestDto;
import com.bank.bankingsystem.dto.AccountResponseDto;
import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.repository.AccountRepository;
import com.bank.bankingsystem.repository.UserRepository;
import com.bank.bankingsystem.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    public AccountResponseDto createAccount(AccountRequestDto requestDto) {

        String currentUserEmail =
                SecurityUtils.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(currentUserEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String accountNumber = generateAccountNumber();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountType(requestDto.getAccountType())
                .balance(requestDto.getInitialBalance())
                .status("ACTIVE")
                .user(user)
                .build();

        Account savedAccount = accountRepository.save(account);

        return mapToResponseDto(savedAccount);
    }

    private String generateAccountNumber() {

        Random random = new Random();

        return "ACC" + (100000 + random.nextInt(900000));
    }

    public AccountResponseDto getAccountByNumber(String accountNumber) {

        String currentUserEmail =
                SecurityUtils.getCurrentUserEmail();

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        if (!account.getUser()
                .getEmail()
                .equalsIgnoreCase(currentUserEmail)) {

            throw new RuntimeException(
                    "Access Denied. This account does not belong to you.");
        }

        return mapToResponseDto(account);
    }

    public List<AccountResponseDto> getAllAccounts() {

        String currentUserEmail =
                SecurityUtils.getCurrentUserEmail();

        List<Account> accounts =
                accountRepository.findByUserEmail(currentUserEmail);

        return accounts.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private AccountResponseDto mapToResponseDto(Account account) {

        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .customerName(account.getUser().getFullName())
                .build();
    }
}