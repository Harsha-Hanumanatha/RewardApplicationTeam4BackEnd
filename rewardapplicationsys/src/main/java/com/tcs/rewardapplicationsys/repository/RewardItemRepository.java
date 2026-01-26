package com.tcs.rewardapplicationsys.repository;

import com.tcs.rewardapplicationsys.entity.RewardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardItemRepository extends JpaRepository<RewardItem, Long> {
    List<RewardItem> findByCategory(String category);
}
