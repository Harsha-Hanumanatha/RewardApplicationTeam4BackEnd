package com.tcs.rewardapplicationsys.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {

    private static final Random random = new Random();

    public static BigDecimal generateRandomAmount(double min, double max) {
        double randomValue = min + (random.nextDouble() * (max - min));
        return new BigDecimal(randomValue).setScale(2, RoundingMode.HALF_UP);
    }

    public static LocalDateTime generateRandomPastDate(int daysToLookBack) {
        long minDay = LocalDateTime.now().minusDays(daysToLookBack).toEpochSecond(java.time.ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofEpochSecond(randomDay, 0, java.time.ZoneOffset.UTC);
    }

//    public static String maskCardNumber(String cardNumber) {
//        if (cardNumber == null || cardNumber.length() < 4) return "XXXX";
//        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
//    }

    // Helper: Logic for 3 Years Rule
    public static boolean isPremiumCustomer(LocalDate doj) {
        if (doj == null) return false;
        // If Period between DOJ and Now is >= 3 Years
        return Period.between(doj, LocalDate.now()).getYears() >= 3;
    }
}
