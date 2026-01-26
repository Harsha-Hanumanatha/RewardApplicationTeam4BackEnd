package com.tcs.rewardapplicationsys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedemptionHistoryDTO {
    private String orderId;
    private List<String> itemsRedeemed;
    private Double pointsRedeemed;
    private LocalDateTime redemptionDate;
    private String status;

}
