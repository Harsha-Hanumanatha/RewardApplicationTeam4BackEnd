package com.tcs.rewardapplicationsys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private LocalDate doj;
    private String email;
    private Long phoneNum;
    private CreditCardDTO creditCard;
    private CustomerType customerType;
    private Boolean isActive;
}
