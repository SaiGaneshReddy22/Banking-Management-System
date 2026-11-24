package com.bank.bankingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bankingsystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUserEmail(String email);
    

    
}
