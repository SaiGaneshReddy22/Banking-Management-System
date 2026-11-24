package com.bank.bankingsystem.repository;

import java.util.List;

// import org.hibernate.query.Page;
// import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bank.bankingsystem.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // List<Transaction> findByFromAccountOrToAccount(String fromAccount,String toAccount);
    
    Page<Transaction> findByFromAccountOrToAccount(String fromAccount,String toAccount,Pageable pageable);

    
    
}
