package com.tcs.rewardapplicationsys.api;

import com.tcs.rewardapplicationsys.entity.RewardItem;
import com.tcs.rewardapplicationsys.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RewardCatalogAPI {
    @Autowired
    private RewardService rewardService;

    @GetMapping("/rewards/catalog")
    public ResponseEntity<List<RewardItem>> getFullCatalog() {
        List<RewardItem> catalog = rewardService.getAllRewards();
        return ResponseEntity.ok(catalog);
    }
}
