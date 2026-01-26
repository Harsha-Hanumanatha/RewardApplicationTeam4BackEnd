package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.RedemptionHistoryDTO;
import com.tcs.rewardapplicationsys.entity.RedemptionHistory;
import com.tcs.rewardapplicationsys.repository.RedemptionHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RedemptionHistoryServiceImpl implements RedemptionHistoryService {

    @Autowired
    RedemptionHistoryRepository redemptionHistoryRepository;

    @Override
    public List<RedemptionHistoryDTO> getHistoryByCard(String cardNumber) {

        List<RedemptionHistory> historyList = redemptionHistoryRepository.findByCreditCardCardNumberOrderByRedemptionDateDesc(cardNumber);

        // Convert Entity -> DTO
        return historyList.stream().map(h -> new RedemptionHistoryDTO(
                h.getOrderId(),
                h.getItemsRedeemed(),
                h.getPointsRedeemed(),
                h.getRedemptionDate(),
                h.getStatus()
        )).collect(java.util.stream.Collectors.toList());
    }
}
