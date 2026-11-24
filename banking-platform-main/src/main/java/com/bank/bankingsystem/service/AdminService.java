package com.bank.bankingsystem.service;

import com.bank.bankingsystem.dto.AccountResponseDto;
import com.bank.bankingsystem.dto.UserResponseDto;
import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.entity.User;
import com.bank.bankingsystem.repository.AccountRepository;
import com.bank.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()

                .stream()

                .map(this::mapUser)

                .toList();
    }

    public List<AccountResponseDto> getAllAccounts() {

        return accountRepository.findAll()

                .stream()

                .map(this::mapAccount)

                .toList();
    }

    public void freezeAccount(String accountNumber) {

        Account account = accountRepository

                .findByAccountNumber(accountNumber)

                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        account.setStatus("FROZEN");

        accountRepository.save(account);
    }

    public void unfreezeAccount(String accountNumber) {

        Account account = accountRepository

                .findByAccountNumber(accountNumber)

                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        account.setStatus("ACTIVE");

        accountRepository.save(account);
    }

    public void deleteUser(Long userId) {

        User user = userRepository

                .findById(userId)

                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    private UserResponseDto mapUser(User user) {

        return UserResponseDto.builder()

                .id(user.getId())

                .fullName(user.getFullName())

                .email(user.getEmail())

                .role(user.getRole())

                .build();
    }

    private AccountResponseDto mapAccount(Account account) {

        return AccountResponseDto.builder()

                .accountNumber(account.getAccountNumber())

                .accountType(account.getAccountType())

                .balance(account.getBalance())

                .status(account.getStatus())

                .customerName(account.getUser().getFullName())

                .build();
    }
}