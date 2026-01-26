package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.CreditCardDTO;
import com.tcs.rewardapplicationsys.dto.CustomerDTO;
import com.tcs.rewardapplicationsys.entity.CreditCard;
import com.tcs.rewardapplicationsys.entity.Customer;
import com.tcs.rewardapplicationsys.entity.Transaction;
import com.tcs.rewardapplicationsys.exception.RewardException;

import java.util.List;

public interface CustomerService {
Integer addCustomer(CustomerDTO customer) throws RewardException;
Integer deleteCustomer(Integer custId) throws RewardException;
    public Customer GetCustomerBycustId(Integer custId) throws RewardException;
    public Customer getCustomerByCardNum(String creditNumber) throws RewardException ;
    public List<Customer> getAllCustomer() throws RewardException ;
//    public Integer addCreditCardToCustomer(Integer customerId, CreditCardDTO cardDto) throws RewardException;
    String addCreditCard(Integer customerId, CreditCardDTO cardDto) throws RewardException;
    String deleteCreditCardFromCustomer(String cardNum) throws RewardException;
    public String activateCustomer(Integer custId) throws RewardException;
    }
