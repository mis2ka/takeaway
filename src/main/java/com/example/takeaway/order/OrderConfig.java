package com.example.takeaway.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository repository) {
        return args -> {
            Order pizza = new Order(
                    "Pizza",
                    "PizzaHut",
                    31.30,
                    false);

            Order sushi = new Order(
                    "Sushi",
                    "Tita",
                    25.0,
                    false);

            Order kebab = new Order(
                    "Kebab",
                    "EatFast",
                    20.0,
                    false);

            repository.saveAll(
                    List.of(pizza, sushi, kebab)
            );
        };
    }
}
