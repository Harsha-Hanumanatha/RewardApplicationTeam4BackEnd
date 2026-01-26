package com.tcs.rewardapplicationsys.api;

import com.tcs.rewardapplicationsys.dto.TransactionDTO;
import com.tcs.rewardapplicationsys.entity.Transaction;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionAPI {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/cards/{cardNumber}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String cardNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByCard(cardNumber);
        return ResponseEntity.ok(transactions);
    }

    // 2. PROCESS TRANSACTION (Cleaned up)
    @PostMapping("/{cardNumber}/transactions/batch")
    public ResponseEntity<Map<String, Object>> processBatch(
            @PathVariable String cardNumber,
            @RequestBody List<TransactionDTO> txnDtos) throws RewardException {

        Map<String, Object> response = transactionService.processBatchTransactions(cardNumber, txnDtos);
        return ResponseEntity.ok(response);
    }
}
