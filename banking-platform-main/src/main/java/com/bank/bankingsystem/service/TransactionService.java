package com.bank.bankingsystem.service;

import com.bank.bankingsystem.dto.TransactionResponseDto;
import com.bank.bankingsystem.dto.TransferRequestDto;
import com.bank.bankingsystem.entity.Account;
import com.bank.bankingsystem.entity.Transaction;
import com.bank.bankingsystem.repository.AccountRepository;
import com.bank.bankingsystem.repository.TransactionRepository;
import com.bank.bankingsystem.security.SecurityUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Transactional
    public String transferMoney(TransferRequestDto requestDto) {

        String currentUserEmail =
                SecurityUtils.getCurrentUserEmail();

        Account sender = accountRepository
                .findByAccountNumber(requestDto.getFromAccount())
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));

        Account receiver = accountRepository
                .findByAccountNumber(requestDto.getToAccount())
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        validateOwnership(sender, currentUserEmail);

        validateAccountStatus(sender);

        validateAccountStatus(receiver);

        validateSameAccount(sender, receiver);

        validateBalance(sender, requestDto);

        sender.setBalance(
                sender.getBalance()
                        .subtract(requestDto.getAmount()));

        receiver.setBalance(
                receiver.getBalance()
                        .add(requestDto.getAmount()));

        accountRepository.save(sender);

        accountRepository.save(receiver);

        Transaction transaction = Transaction.builder()
                .fromAccount(sender.getAccountNumber())
                .toAccount(receiver.getAccountNumber())
                .amount(requestDto.getAmount())
                .transactionType("TRANSFER")
                .status("SUCCESS")
                .transactionTime(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return "Money transferred successfully";
    }

    public List<TransactionResponseDto> getAllTransactions() {

        return transactionRepository.findAll()

                .stream()

                .map(this::mapTransaction)

                .toList();
    }

    public Page<TransactionResponseDto> getTransactionsByAccount(
            String accountNumber,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("transactionTime")
                                .descending());

        return transactionRepository

                .findByFromAccountOrToAccount(
                        accountNumber,
                        accountNumber,
                        pageable)

                .map(this::mapTransaction);
    }

    private void validateOwnership(
            Account sender,
            String currentUserEmail) {

        if (!sender.getUser()
                .getEmail()
                .equalsIgnoreCase(currentUserEmail)) {

            throw new RuntimeException(
                    "You are not authorized to transfer money from this account.");
        }
    }

    private void validateAccountStatus(Account account) {

        if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {

            throw new RuntimeException(
                    "Account is not active.");
        }
    }

    private void validateSameAccount(
            Account sender,
            Account receiver) {

        if (sender.getAccountNumber()
                .equals(receiver.getAccountNumber())) {

            throw new RuntimeException(
                    "Sender and Receiver accounts cannot be the same.");
        }
    }

    private void validateBalance(
            Account sender,
            TransferRequestDto requestDto) {

        if (sender.getBalance()
                .compareTo(requestDto.getAmount()) < 0) {

            throw new RuntimeException(
                    "Insufficient balance.");
        }
    }

    private TransactionResponseDto mapTransaction(
            Transaction transaction) {

        return TransactionResponseDto.builder()

                .fromAccount(transaction.getFromAccount())

                .toAccount(transaction.getToAccount())

                .amount(transaction.getAmount())

                .transactionType(transaction.getTransactionType())

                .status(transaction.getStatus())

                .transactionTime(transaction.getTransactionTime())

                .build();
    }
}