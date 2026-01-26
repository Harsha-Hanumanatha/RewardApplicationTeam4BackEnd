package com.tcs.rewardapplicationsys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(unique = true, nullable = false)
    private String cardNumber;

    private Boolean isCardActive;
    private Double rewardPoints;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "cust_id_fk")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RedemptionHistory> redemptionHistory = new ArrayList<>();
}