package com.tcs.rewardapplicationsys.repository;

import com.tcs.rewardapplicationsys.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepo extends JpaRepository<CreditCard,Integer> {
    CreditCard findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
}
