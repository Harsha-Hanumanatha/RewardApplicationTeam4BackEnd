package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.RedemptionHistoryDTO;

import java.util.List;

public interface RedemptionHistoryService {
    List<RedemptionHistoryDTO> getHistoryByCard(String cardNumber);
}
