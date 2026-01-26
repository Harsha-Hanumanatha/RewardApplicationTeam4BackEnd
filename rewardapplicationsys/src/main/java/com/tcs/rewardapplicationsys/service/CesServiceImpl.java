package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.CesUserDTO;
import com.tcs.rewardapplicationsys.dto.UserRole;
import com.tcs.rewardapplicationsys.entity.CesUser;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.repository.CesUserRepo;
import com.tcs.rewardapplicationsys.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CesServiceImpl implements CesService {

    @Autowired
    CesUserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    // 1. Login Logic
    @Override
    public String login(String username, String rawPassword) throws RewardException {
        CesUser user = userRepo.findByUserName(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return jwtUtils.generateToken(user.getUserName(), user.getRole().name());
        }
        throw new RewardException("Invalid Username or Password");
    }

    public void addCesUser(CesUserDTO dto) throws RewardException {
        if(userRepo.findByUserName(dto.getUserName()) != null) {
            throw new RewardException("Username already exists");
        }

        CesUser user = new CesUser();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.CES_USER);

        userRepo.save(user);
    }

    @Override
    public List<CesUser> getAllUsers() {
        return userRepo.findAll();
    }

    // 3. Delete User
    @Override
    public String deleteByUserName(String username) throws RewardException {
        CesUser user = userRepo.findByUserName(username);
        if (user != null) {
            if (user.getRole() == UserRole.CES_USER) {
                userRepo.delete(user);
                return "User " + username + " deleted successfully.";
            } else {
                throw new RewardException("Administrator accounts cannot be deleted.");
            }
        } else {
            throw new RewardException("User not found: " + username);
        }
    }
}



