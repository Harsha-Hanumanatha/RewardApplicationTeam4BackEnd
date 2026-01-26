package com.tcs.rewardapplicationsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_details")
public class Transaction {
    @Id
    private String transactionId;

    private String item;
    private Double amount;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime transactionDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "card_id_fk")
    private CreditCard creditCard;
}