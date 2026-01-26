package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.RedemptionRequest;
import com.tcs.rewardapplicationsys.dto.RedemptionResponse;
import com.tcs.rewardapplicationsys.entity.RewardItem;
import com.tcs.rewardapplicationsys.exception.RewardException;

import java.util.List;

public interface RewardService {
    List<RewardItem> getAllRewards();

    public RedemptionResponse redeemPoints(String cardNumber, RedemptionRequest request) throws RewardException;
//    List<RewardItem> getRewardsByCategory(String category);
}
