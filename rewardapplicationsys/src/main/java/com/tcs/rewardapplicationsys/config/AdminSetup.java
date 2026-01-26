package com.tcs.rewardapplicationsys.config;

import com.tcs.rewardapplicationsys.dto.UserRole;
import com.tcs.rewardapplicationsys.entity.CesUser;
import com.tcs.rewardapplicationsys.repository.CesUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSetup {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initAdmin(CesUserRepo userRepo) {
        return args -> {
            if (userRepo.findByUserName("superadmin") == null) {
                System.out.println("--- CREATING SUPER ADMIN ---");

                CesUser admin = new CesUser();
                admin.setUserName("superadmin");
                // The password will be "admin123"
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(UserRole.ADMIN);

                userRepo.save(admin);
                System.out.println("--- SUPER ADMIN CREATED (User: superadmin / Pass: admin123) ---");
            } else {
                System.out.println("--- SUPER ADMIN ALREADY EXISTS ---");
            }
        };
    }
}
