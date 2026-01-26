package com.tcs.rewardapplicationsys.api;

import com.tcs.rewardapplicationsys.dto.*;
import com.tcs.rewardapplicationsys.entity.Customer;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.service.CustomerService;
import com.tcs.rewardapplicationsys.service.RedemptionHistoryService;
import com.tcs.rewardapplicationsys.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;
    @Autowired
    Environment env;
    @Autowired
    RedemptionHistoryService redemptionHistoryService;
    @Autowired
    RewardService rewardService;

    @GetMapping("/customers/{customerId}/cards/{cardNumber}/redemptions/history")
    public ResponseEntity<List<RedemptionHistoryDTO>> getRedemptionHistory(
            @PathVariable Long customerId,
            @PathVariable String cardNumber) {

        System.out.println("Fetching history for Card Number: " + cardNumber);
        List<RedemptionHistoryDTO> history = redemptionHistoryService.getHistoryByCard(cardNumber);
        return ResponseEntity.ok(history);
    }

//    @PostMapping("/customers/{customerId}/cards/{cardNumber}/redemptions")
//    public ResponseEntity<RedemptionResponse> redeemPoints(
//            @PathVariable Long customerId,
//            @PathVariable String cardNumber,
//            @RequestBody RedemptionRequest request) throws RewardException {
//
//        System.out.println("Processing Redemption for Card ID: " + cardNumber);
//
//        // Call the service to deduct points and save history
//        RedemptionResponse response = rewardService.redeemPoints(cardNumber, request);
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer) throws RewardException {
        Integer id=customerService.addCustomer(customer);
        String msg = env.getProperty("API.Customer.Added.Successfully");
        return new ResponseEntity<>(msg + id ,HttpStatus.OK);

    }

    @PutMapping("/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer custId) throws RewardException {
        Integer customerId = customerService.deleteCustomer(custId);
        String msg = env.getProperty("API.Customer.Deleted.Successfully");
        return new ResponseEntity<>(msg + customerId, HttpStatus.OK);
    }

    @PutMapping("/activate/{custId}")
    public ResponseEntity<String> activateCustomer(@PathVariable Integer custId) throws RewardException {
        String response = customerService.activateCustomer(custId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{custId}")
    public ResponseEntity<Customer> getCustomers(@PathVariable Integer custId) throws RewardException {
        Customer Cust = customerService.GetCustomerBycustId(custId);
        return new ResponseEntity<>(Cust, HttpStatus.OK);

    }


    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<Customer> getCustomerByCardNumber(@PathVariable String cardNumber) throws RewardException {
        Customer cust = customerService.getCustomerByCardNum(cardNumber);
        return new ResponseEntity<>(cust, HttpStatus.OK);
    }
    @GetMapping("/allCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer() throws RewardException {
        List<Customer> Cust = customerService.getAllCustomer();
        return new ResponseEntity<>(Cust, HttpStatus.OK);
    }


    //creditcard
    @PostMapping("/{customerId}/cards")
    public ResponseEntity<String> addCard(@PathVariable Integer customerId, @RequestBody CreditCardDTO cardDto) throws RewardException {
        customerService.addCreditCard(customerId, cardDto);
        return new ResponseEntity<>("Card added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/delete/{cardNumber}")
    public ResponseEntity<String> deleteCard(@PathVariable String cardNumber) throws RewardException {
        String cn=customerService.deleteCreditCardFromCustomer(cardNumber);
        return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);
    }

}


