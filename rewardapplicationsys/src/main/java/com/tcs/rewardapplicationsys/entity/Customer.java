package com.tcs.rewardapplicationsys.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tcs.rewardapplicationsys.dto.CustomerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String firstName;
    private String lastName;
    private LocalDate doj;
    private String email;
    private Long phoneNum;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CreditCard> creditCard;
}