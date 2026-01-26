package com.tcs.rewardapplicationsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RedemptionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderId;

    @ElementCollection
    private List<String> itemsRedeemed;

    private Double pointsRedeemed;


    @Column(columnDefinition = "DATETIME")
    private LocalDateTime redemptionDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "card_id_fk")
    @JsonIgnore
    private CreditCard creditCard;

    public RedemptionHistory(String orderId, List<String> itemNames, Double requiredPoints, LocalDateTime now, String success) {
        this.orderId = orderId;
        this.itemsRedeemed = itemNames;
        this.pointsRedeemed = requiredPoints;
        this.redemptionDate = now;
        this.status = success;
    }
}