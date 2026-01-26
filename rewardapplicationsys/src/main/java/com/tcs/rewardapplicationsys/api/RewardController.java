package com.tcs.rewardapplicationsys.api;

import com.tcs.rewardapplicationsys.dto.RedemptionRequest;
import com.tcs.rewardapplicationsys.dto.RedemptionResponse;
import com.tcs.rewardapplicationsys.entity.RewardItem;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1") // Base URL matches your Frontend Service
@CrossOrigin(origins = "http://localhost:4200")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // 1. Get Catalog
    // Frontend calls: this.http.get(... + "/rewards")
    @GetMapping("/rewards")
    public ResponseEntity<List<RewardItem>> getRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    // 2. Redeem Points
    // Frontend calls: this.http.post(... + "/cards/{cardNumber}/redeem")
    @PostMapping("/cards/{cardNumber}/redeem")
    public ResponseEntity<RedemptionResponse> redeemPoints(
            @PathVariable String cardNumber,
            @RequestBody RedemptionRequest request) throws RewardException {

        try {
            RedemptionResponse response = rewardService.redeemPoints(cardNumber, request);
            return ResponseEntity.ok(response);
        } catch (RewardException e) {
            // If service throws error (e.g., Insufficient Balance), return 400 Bad Request
            // You might need a custom error object here, but throwing the exception is often handled by a global handler
            throw e;
        }
    }
}
