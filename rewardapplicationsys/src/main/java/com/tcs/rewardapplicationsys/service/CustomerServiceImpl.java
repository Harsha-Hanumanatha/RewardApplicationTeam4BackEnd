package com.tcs.rewardapplicationsys.service;

import com.tcs.rewardapplicationsys.dto.CreditCardDTO;
import com.tcs.rewardapplicationsys.dto.CustomerDTO;
import com.tcs.rewardapplicationsys.dto.CustomerType;
import com.tcs.rewardapplicationsys.entity.CreditCard;
import com.tcs.rewardapplicationsys.entity.Customer;
import com.tcs.rewardapplicationsys.exception.RewardException;
import com.tcs.rewardapplicationsys.repository.CreditCardRepo;
import com.tcs.rewardapplicationsys.repository.CustomerRepo;
import com.tcs.rewardapplicationsys.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo  customerRepo;
    @Autowired
    CreditCardRepo creditCardRepo;

    @Override
    public Integer addCustomer(CustomerDTO customer) throws RewardException {

        if (customer.getCustomerId() != null) {
            Optional<Customer> customer1 = customerRepo.findById(customer.getCustomerId());
            if (customer1.isPresent()) {
                throw new RewardException("Customer already exists");
            }
        }

        Customer cust = new Customer();
        cust.setDoj(customer.getDoj());
        cust.setFirstName(customer.getFirstName());
        cust.setLastName(customer.getLastName());
        String num= String.valueOf(customer.getPhoneNum());
        if(Validate.isValidPhone(num)){
            cust.setPhoneNum(customer.getPhoneNum());
        }
        if(Validate.isValidEmail(customer.getEmail())){
            cust.setEmail(customer.getEmail());
        }

        if (cust.getDoj() != null && cust.getDoj().isBefore(LocalDate.now().minusYears(3))) {
            cust.setCustomerType(CustomerType.PREMIUM);
        } else {
            cust.setCustomerType(CustomerType.REGULAR);
        }

        cust.setIsActive(true);
        customerRepo.save(cust);
        return cust.getCustomerId();
    }

    @Override
    public Integer deleteCustomer(Integer custId) throws RewardException {
        Optional<Customer> customer1=customerRepo.findById(custId);
        if(customer1.isEmpty()){
            throw new RewardException("Customer does not exist");
        }
        else{
            customer1.get().setIsActive(false);
            customerRepo.save(customer1.get());
        }
        return customer1.get().getCustomerId();
    }

    @Override
    public Customer GetCustomerBycustId(Integer custId) throws RewardException {
        Optional<Customer> cust=customerRepo.findById(custId);

        if(cust.isEmpty()){
            throw new RewardException("Customer Not Found");
        }
        else{
            return cust.get();

        }
    }

    @Override
    public Customer getCustomerByCardNum(String cardNumber) throws RewardException {
        Customer customer = customerRepo.findByCreditCardCardNumber(cardNumber);
        if(customer==null){
            throw new RewardException("Customer Not Found");
        }
        else{
            return customer;
        }
    }

    @Override
    public List<Customer> getAllCustomer() throws RewardException {
        List<Customer> customer=customerRepo.findAll();
        if(customer.isEmpty()){
            throw new RewardException("Customer Not Found");
        }
        return customer;
    }

    @Override
    public String addCreditCard(Integer customerId, CreditCardDTO cardDto) throws RewardException {

        CreditCard existingCard = creditCardRepo.findByCardNumber(cardDto.getCardNumber());

        if (existingCard != null) {
            if (existingCard.getIsCardActive()) {
                throw new RewardException("Duplicate Error: Card " + cardDto.getCardNumber() + " is already linked!");
            }
            else {
                if (!existingCard.getCustomer().getCustomerId().equals(customerId)) {
                    throw new RewardException("This card number belongs to a different customer history!");
                }
                existingCard.setIsCardActive(true);
                creditCardRepo.save(existingCard);
                return "Card Relinked/Restored Successfully";
            }
        }

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RewardException("Customer not found with ID: " + customerId));

        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(cardDto.getCardNumber());
        newCard.setIsCardActive(true);
        newCard.setRewardPoints(0.0);
        newCard.setCustomer(customer);

        creditCardRepo.save(newCard);

        return "Credit Card Linked Successfully";
    }


    @Override
    public String deleteCreditCardFromCustomer(String creditCardNum) throws RewardException {
        Customer customer1=customerRepo.findByCreditCardCardNumber(creditCardNum);
            if(customer1==null){
                throw new RewardException("There is no Customer with given card number");
            }
            else{
               CreditCard card=creditCardRepo.findByCardNumber(creditCardNum);
               card.setIsCardActive(false);
               creditCardRepo.save(card);
            }
           return creditCardNum;
        }

    @Override
    public String activateCustomer(Integer custId) throws RewardException {
        Customer customer = customerRepo.findById(custId)
                .orElseThrow(() -> new RewardException("Customer not found"));

        customer.setIsActive(true);
        customerRepo.save(customer);

        return "Customer Reactivated Successfully";
    }

}





