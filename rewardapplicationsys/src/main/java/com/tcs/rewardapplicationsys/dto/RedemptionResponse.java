package com.tcs.rewardapplicationsys.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedemptionResponse {

    private String status;
    private String orderId;
    private Double pointsBurned;
    private Double remainingBalance;
}
