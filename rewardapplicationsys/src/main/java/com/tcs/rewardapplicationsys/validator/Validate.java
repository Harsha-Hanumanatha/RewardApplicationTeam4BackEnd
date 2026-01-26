package com.tcs.rewardapplicationsys.validator;

import com.tcs.rewardapplicationsys.exception.RewardException;

import java.util.regex.Pattern;

public class Validate {
    public static boolean isValidEmail(String email) throws RewardException {
        String regex = "^[A-Za-z0-9_]+@[A-Za-z0-9_]+(\\.[A-Za-z0-9_]+)+$";

        if(Pattern.matches(regex,email))
        {
            return true;
        }
        else {

            throw  new RewardException("Validator.INVALID_EMAIL");
        }
    }

    public static boolean isValidPhone(String Phone) throws RewardException {
        String regex = "^(?!([0-9])\\1{9})\\d{10}$";

        if(Pattern.matches(regex,Phone))
        {
            return true;
        }
        else{

            throw  new RewardException("Validator.INVALID_PHONENUMBER");
        }
    }
    public static boolean isValidCardNumber(String cardNumber) throws RewardException {
        String regex = "^[0-9]{16}$";
        if(Pattern.matches(regex,cardNumber))
        {
            return true;
        }
        else{
            throw new RewardException("Validator.Invalid_CARDNUMBER");
        }
    }
}
