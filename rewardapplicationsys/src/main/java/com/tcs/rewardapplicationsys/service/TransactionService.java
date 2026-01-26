package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.TransactionDTO;
import com.tcs.rewardapplicationsys.entity.Transaction;
import com.tcs.rewardapplicationsys.exception.RewardException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    Map<String, Object> processBatchTransactions(String cardNumber, List<TransactionDTO> transactionDTOs) throws RewardException;

    List<Transaction> getTransactionsByCard(String cardNumber);

    //String processTransaction(String cardNumber, TransactionDTO txnDto) throws RewardException;
}
