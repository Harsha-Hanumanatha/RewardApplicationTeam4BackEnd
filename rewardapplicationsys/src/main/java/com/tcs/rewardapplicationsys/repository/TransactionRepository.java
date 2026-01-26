package com.tcs.rewardapplicationsys.repository;

import com.tcs.rewardapplicationsys.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    List<Transaction> findByCreditCardCardNumber(String cardNumber);
}
