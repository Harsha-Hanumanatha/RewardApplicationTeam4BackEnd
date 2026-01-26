package com.tcs.rewardapplicationsys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedemptionRequest {
    private List<String> itemNames;
    private Double totalPoints;
}
