package com.tcs.rewardapplicationsys;

import com.tcs.rewardapplicationsys.entity.RewardItem;
import com.tcs.rewardapplicationsys.repository.RewardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RewardapplicationsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardapplicationsysApplication.class, args);
	}

}