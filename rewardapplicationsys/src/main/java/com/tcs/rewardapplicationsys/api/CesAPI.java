package com.tcs.rewardapplicationsys.api;

import com.tcs.rewardapplicationsys.dto.CesUserDTO;
import com.tcs.rewardapplicationsys.entity.CesUser;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.service.CesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class CesAPI {

    @Autowired
    CesService cesService;

    // LOGIN ENDPOINT (Public)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CesUserDTO loginDto) throws RewardException {
        String token = cesService.login(loginDto.getUserName(), loginDto.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    // Inside CesAPI class
    @GetMapping("/all")
    public ResponseEntity<List<CesUser>> getAllUsers() {
        return ResponseEntity.ok(cesService.getAllUsers());
    }

    // ADD USER (Restricted to ADMIN by SecurityConfig)
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody CesUserDTO cesUser) throws RewardException {
        cesService.addCesUser(cesUser);
        return new ResponseEntity<>("Sub-CES User created successfully", HttpStatus.CREATED);
    }

    // DELETE USER (Restricted to ADMIN by SecurityConfig)
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) throws RewardException {
        String message = cesService.deleteByUserName(username);
        return ResponseEntity.ok(message);
    }
}
