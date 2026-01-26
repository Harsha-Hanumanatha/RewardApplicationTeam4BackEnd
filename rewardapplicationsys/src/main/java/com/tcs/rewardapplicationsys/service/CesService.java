package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.CesUserDTO;
import com.tcs.rewardapplicationsys.entity.CesUser;
import com.tcs.rewardapplicationsys.exception.RewardException;

import java.util.List;

public interface CesService {
    // 1. Login Logic
    String login(String username, String rawPassword) throws RewardException;

    public void addCesUser(CesUserDTO dto) throws RewardException;

    // Inside CesServiceImpl class
    List<CesUser> getAllUsers();

    public String deleteByUserName(String username) throws RewardException;


}
