package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.TransactionDTO;
import com.tcs.rewardapplicationsys.entity.CreditCard;
import com.tcs.rewardapplicationsys.entity.Customer;
import com.tcs.rewardapplicationsys.entity.Transaction;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.repository.CreditCardRepo; // or CreditCardRepository
import com.tcs.rewardapplicationsys.repository.TransactionRepository;
import com.tcs.rewardapplicationsys.utility.DataHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CreditCardRepo creditCardRepository; // Ensure this name matches your actual Repo bean

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByCard(String cardNumber) {
        return transactionRepository.findByCreditCardCardNumber(cardNumber);
    }

    // --- MERGED LOGIC: Takes DTO, Saves Txn, AND Calculates Points ---
    @Override
    public Map<String, Object> processBatchTransactions(String cardNumber, List<TransactionDTO> txnDtos) throws RewardException {

        // 1. Fetch Card (Only Once!)
        CreditCard card = creditCardRepository.findByCardNumber(cardNumber);
        if(card == null) throw new RewardException("Card not found");
        if(!card.getIsCardActive()) throw new RewardException("Card is inactive");

        Customer customer = card.getCustomer();
        boolean isPremium = DataHelper.isPremiumCustomer(customer.getDoj());
        double rate = isPremium ? 0.10 : 0.05;

        List<Transaction> txnsToSave = new ArrayList<>();
        double totalPointsToAdd = 0.0;

        // 2. Loop through all incoming transactions
        for (TransactionDTO dto : txnDtos) {
            Transaction txn = new Transaction();

            // Generate ID
            txn.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            txn.setItem(dto.getItem());
            txn.setAmount(dto.getAmount());

            // Date Logic
            if (dto.getTransactionDate() != null) {
                txn.setTransactionDate(dto.getTransactionDate());
            } else {
                txn.setTransactionDate(LocalDateTime.now());
            }

            txn.setStatus("Success");
            txn.setCreditCard(card); // Link

            // Calculate Points for this specific transaction
            double points = dto.getAmount() * rate;
            totalPointsToAdd += points;

            txnsToSave.add(txn);
        }

        // 3. Update Card Balance (Only Once!)
        double currentPoints = card.getRewardPoints() != null ? card.getRewardPoints() : 0.0;
        card.setRewardPoints(currentPoints + totalPointsToAdd);

        // 4. Save Everything
        transactionRepository.saveAll(txnsToSave); // Bulk Save
        creditCardRepository.save(card);           // Update Points

        // Return a summary
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Processed " + txnDtos.size() + " transactions.");
        response.put("pointsAdded", totalPointsToAdd);
        return response;
    }
}