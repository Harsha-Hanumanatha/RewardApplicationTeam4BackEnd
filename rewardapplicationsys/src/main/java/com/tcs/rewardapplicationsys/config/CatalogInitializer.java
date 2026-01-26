package com.tcs.rewardapplicationsys.config;

import com.tcs.rewardapplicationsys.entity.RewardItem;
import com.tcs.rewardapplicationsys.repository.RewardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class CatalogInitializer implements CommandLineRunner {

    @Autowired
    private RewardItemRepository rewardItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only load if table is empty to avoid duplicates on restart
        if (rewardItemRepository.count() == 0) {
            System.out.println("Initializing Full Reward Catalog...");

            List<RewardItem> allItems = Arrays.asList(
                    // --- Gift Cards ---
                    new RewardItem(null, "Google Play Gift Card", "Gift Cards", 5000, "assets/gc-google.png"),
                    new RewardItem(null, "Apple Gift Card", "Gift Cards", 6000, "assets/gc-apple.png"),
                    new RewardItem(null, "Amazon Gift Card", "Gift Cards", 4500, "assets/gc-amazon.png"),
                    new RewardItem(null, "Flipkart Gift Card", "Gift Cards", 4500, "assets/gc-flipkart.png"),
                    new RewardItem(null, "Swiggy Gift Card", "Gift Cards", 3500, "assets/gc-swiggy.png"),
                    new RewardItem(null, "Zomato Gift Card", "Gift Cards", 3500, "assets/gc-zomato.png"),

                    // --- Travel & Holidays ---
                    new RewardItem(null, "Trip to Manali", "Travel", 40000, "assets/tr-manali.jpg"),
                    new RewardItem(null, "Trip to Kanyakumari", "Travel", 30000, "assets/tr-kanyakumari.jpg"),
                    new RewardItem(null, "Goa Beach Holiday", "Travel", 45000, "assets/tr-goa.jpg"),
                    new RewardItem(null, "Jaipur Heritage Trip", "Travel", 28000, "assets/tr-jaipur.jpg"),
                    new RewardItem(null, "Ooty Hill Station Trip", "Travel", 38000, "assets/tr-ooty.jpg"),

                    // --- Shopping & Electronics ---
                    new RewardItem(null, "Bluetooth Headphones", "Electronics", 12000, "assets/el-headphone.jpg"),
                    new RewardItem(null, "Smart Watch", "Electronics", 18000, "assets/el-watch.jpg"),
                    new RewardItem(null, "Wireless Earbuds", "Electronics", 15000, "assets/el-earbuds.jpg"),
                    new RewardItem(null, "Smartphone Voucher", "Electronics", 22000, "assets/el-phone.jpg"),
                    new RewardItem(null, "Laptop Bag", "Electronics", 6000, "assets/el-bag.jpg"),

                    // --- Dining & Lifestyle ---
                    new RewardItem(null, "Dinner for Two", "Dining", 8000, "assets/dn-dinner.jpg"),
                    new RewardItem(null, "Café Voucher", "Dining", 4000, "assets/dn-cafe.jpg"),
                    new RewardItem(null, "Movie Tickets", "Dining", 5000, "assets/dn-movie.jpg"),
                    new RewardItem(null, "Spa Voucher", "Dining", 10000, "assets/dn-spa.jpg"),

                    // --- Health & Fitness ---
                    new RewardItem(null, "Gym Membership (3 months)", "Fitness", 20000, "assets/ft-gym.jpg"),
                    new RewardItem(null, "Yoga Classes", "Fitness", 7000, "assets/ft-yoga.jpg"),
                    new RewardItem(null, "Fitness Band", "Fitness", 9000, "assets/ft-band.jpg"),
                    new RewardItem(null, "Nutrition Consultation", "Fitness", 6000, "assets/ft-nutrition.jpg"),

                    // --- Learning & Subscriptions ---
                    new RewardItem(null, "Online Course Voucher", "Learning", 10000, "assets/lr-course.jpg"),
                    new RewardItem(null, "E-Book Subscription", "Learning", 5000, "assets/lr-ebook.jpg"),
                    new RewardItem(null, "Coding Platform Access", "Learning", 12000, "assets/lr-coding.jpg"),
                    new RewardItem(null, "Music Subscription", "Learning", 4000, "assets/lr-music.jpg")
            );

            rewardItemRepository.saveAll(allItems);
            System.out.println("Catalog Loaded Successfully: " + allItems.size() + " items added.");
        }
    }
}
